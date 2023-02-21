<%--
  Created by IntelliJ IDEA.
  User: mathieu
  Date: 15/02/2023
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                java.util.List"%>
<%@ page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidat" %>
<%@ page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Candidat" %>

<%
    IServiceCandidat serviceCandidat = (IServiceCandidat) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidat");
    List<Candidat> candidatures = serviceCandidat.listeCandidat();
%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"><h3><i class="fa fa-th"></i> Liste des candidatures référencées </h3></div> <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <!--
                          Nom des colonnes
                        -->
                        <thead>
                        <tr>
                            <th>Identifiant</th>
                            <th>Nom</th>
                            <th>Prenom</th>
                            <th>Adresse postale (ville)</th>
                            <th>mail</th>
                            <th>Niveau de qualification</th>
                            <th>Date de depot</th>
                        </tr>
                        </thead>
                        <!--
                          Contenu du tableau
                        -->
                        <tbody>
                        <%
                            for(Candidat candidature : candidatures)
                            {
                        %>
                        <tr>
                            <td>CAND_<%=candidature.getIdCandidat()%></td>
                            <td><%=candidature.getNom()%></td>
                            <td><%=candidature.getPrenom()%></td>
                            <td><%=candidature.getAdressePostale()%></td>
                            <td><%=candidature.getAdresseEmail()%></td>
                            <td><%=candidature.getNiveauQualification().getIntituleQualification()%></td>
                            <td><%=candidature.getDateDepot()%></td>
                            <td align="center"><a href="template.jsp?action=infos_candidature&id=<%=candidature.getIdCandidat()%>"><i class="fa fa-eye fa-lg"></i></a></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div> <!-- /.table-responsive -->
            </div> <!-- /.panel-body -->
        </div> <!-- /.panel -->
    </div> <!-- /.col-lg-12 -->
</div> <!-- /.row -->
