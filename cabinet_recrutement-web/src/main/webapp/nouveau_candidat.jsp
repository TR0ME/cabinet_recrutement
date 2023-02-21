<%--
  Created by IntelliJ IDEA.
  User: mathieu
  Date: 15/02/2023
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceSecteur"%>
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                java.util.List"%>
<%@ page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidat" %>
<%@ page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Candidat" %>
<%@ page import="java.io.PrintWriter" %>

<%
    IServiceCandidat serviceCandidature = (IServiceCandidat) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
    List<Candidat> candidatures = serviceCandidature.listeCandidat();
    List<NiveauQualification> niveauqualif = serviceCandidature.listeNiveauQualification();
    List<SecteurActivite> secteurActs = serviceCandidature.listeSecteurs();
    IServiceSecteur serviceSecteur = (IServiceSecteur) ServicesLocator.getInstance().getRemoteInterface("ServiceSecteur");
%>
<!-- base code demo -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"><h3><i class="fa fa-user"></i>Référencer une nouvelle candidature</h3></div> <!-- /.panel-heading -->
            <div class="panel-body">

                <div class="col-lg-offset-2 col-lg-8
                        col-xs-12">
                    <form role="form" action="template.jsp" method="get">
                        <input type="hidden" name="action" value="nouvelle_candidature" />
                        <div class="form-group">
                            <input class="form-control" placeholder="Nom" name="nom" />
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Prenom" name="prenom" />
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Date de naissance (format jj/mm/aaaa)" name="date_naissance" />
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Adresse postale (ville)" name="adresse_postale" />
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Adresse email" name="adresse_email" />
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" placeholder="Curriculum vitæ" rows="5" name="cv"></textarea>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <label>Niveau de qualification</label>
                                <small>
                                    <% for(NiveauQualification nq : niveauqualif){%>

                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="niveau" value=<%=nq.getIdQualification() %> /><%=nq.getIntituleQualification() %>
                                        </label>
                                    </div>
                                    <%} %>
                                </small>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <div class="form-group">
                                <label>Secteur(s) d'activité</label>
                                <small>
                                    <table border="0" width="100%">
                                        <!-- Un petit système à la volée pour mettre les checkboxes en deux colonnes...  -->
                                        <%
                                            int i=0;
                                            for(SecteurActivite s : secteurActs) {
                                                i++;
                                                if(i%2 == 0) {%>

                                        <td>
                                            <input type="checkbox" name="secteur" value=<%=s.getIdSecteur()%> /><%=s.getIntituleActivite()%>
                                        </td>
                                        </tr>
                                        <%} else{%>
                                        <tr>
                                            <td>
                                                <input type="checkbox" name="secteur" value=<%=s.getIdSecteur()%> /><%=s.getIntituleActivite()%>
                                            </td>

                                                <%} %>
                                                <%}
                      		            if(i%2==1) { %>
                                        </tr>
                                        <%
                                            }
                                        %>

                                    </table>
                                </small>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-success btn-circle btn-lg" name="submit-insertion"><i class="fa fa-check"></i></button>
                            <button type="reset"  class="btn btn-warning btn-circle btn-lg"><i class="fa fa-times"></i></button>
                        </div>
                    </form>
                </div>

            </div> <!-- /.panel-body -->
        </div> <!-- /.panel -->
    </div> <!-- /.col-lg-12 -->
</div> <!-- /.row -->
<%
    //?action=nouvelle_candidature&date_naissance=a&adresse_postale=b&adresse_email=c&cv=d&niveau=5&secteur=23&submit-insertion=
//Pas de nom prenom car on a zappe che pas pk...
    if(request.getParameter("submit-insertion") != null){
        if(request.getParameter("date_naissance").matches("((0?[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4})")
                && request.getParameter("adresse_postale").length() >0
                && request.getParameter("adresse_email").matches("[a-z0-9.-]+@[a-z0-9.-]+\\.[a-zA-Z]{2,6}")
                && request.getParameter("cv").length() >0
                && request.getParameter("niveau").length() >0
                && request.getParameter("secteur").length() >0
                && request.getParameter("nom").length() >0
                && request.getParameter("prenom").length() >0
        ){

            Candidat cand_ok = new Candidat(
                    request.getParameter("prenom"),
                    request.getParameter("nom"),
                    request.getParameter("adresse_email"),
                    request.getParameter("adresse_postale"),
                    request.getParameter("cv"),
                    serviceCandidature.getCurrentDate(),
                    serviceCandidature.convertDate(request.getParameter("date_naissance")),
                    serviceCandidature.findNQByID(Integer.parseInt(request.getParameter("niveau")))
            );
            cand_ok = serviceCandidature.execPersist(cand_ok);
            //out.println("iDDDDDDDDDDDDDDDDDDDDDDD = "+cand_ok.getId());
            serviceCandidature.majSecteursActivites(request.getParameterValues("secteur"), cand_ok.getIdCandidat());
            //rediriger vers un truc, persite returne lentreprise et donc l ID --cllg
            out.println("<h1 style=\"color: green;text-align: center\"> Candidature ajoutée ! </h1>");

        } else {
            if (!request.getParameter("date_naissance").matches("((0?[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4})")
                    && request.getParameter("date_naissance").length() > 0) {
                out.println("<h2 style=\"color: red;text-align: center\"> Date  au incorrecte !  (jj/mm/aaaa) ! </h2>");
            } else if (!request.getParameter("adresse_email").matches("[a-z0-9.-]+@[a-z0-9.-]+\\.[a-zA-Z]{2,6}")
                    && request.getParameter("adresse_email").length() > 0) {
                out.println(
                        "<h2 style=\"color: red;text-align: center\"> adresse email incorrecte ! (exemple@fournisseur.ex) </h2>");
            } else
                out.println("<h2 style=\"color: red;text-align: center\"> merci de rentrer des champs ! </h2>");
        }
    }
%>