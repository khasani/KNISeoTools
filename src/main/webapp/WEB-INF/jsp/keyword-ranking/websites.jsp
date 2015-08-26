<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Websites - Keyword Ranking</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>


<div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Websites list
                    <div class="pull-right">
                    	<button class="btn btn-default btn-s" onclick="openAddForm()"><i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-hover" id="datatable-websites">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>URL</th>
                                    <th>Category</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${fn:length(websitesList) > 0}">
	                                <c:forEach var="i" begin="0" end="${fn:length(websitesList)-1}">
		                                <tr class="odd gradeX">
		                                    <td>
		                                    	<a href="<c:url value="/keyword-ranking/site?id=${websitesList[i].id}"/>"><c:out value="${websitesList[i].name}"/></a> - 
		                                    	<a href="#"><i class="fa fa-pencil-square-o fa-fw"></i></a>
		                                    	<a href="#"><i class="fa fa-trash-o fa-fw"></i></a>
		                                    </td>
		                                    <td><a href="${websitesList[i].url}" target="_blank"><c:out value="${websitesList[i].url}"/></a></td>
		                                    <td><c:out value="${websitesList[i].category.name}"/></td>
		                                </tr>
									</c:forEach>
								</c:if>
                                
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                    
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
            

            <!-- Modal -->           
            <div style="display: none;" class="modal fade" id="modal_add_site" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <form role="form" id="form_add_website" method="POST" action="<c:url value="/keyword-ranking/websites"/>">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">Add a new website</h4>
                        </div>
                        <div class="modal-body">
                                          	                                   
                               <div id="form_error_name" class="form-group input-group">     
                                   <input class="form-control" id="name" name="name" placeholder="Site name" type="text"/>
                                   <span class="input-group-addon">Name</span>
                               </div>
                               <div id="form_error_url" class="form-group input-group">     
                                   <input class="form-control" id="url" name="url" placeholder="http://" type="text"/>
                                   <span class="input-group-addon">URL</span>
                               </div>
                               <div id="form_error_category" class="form-group input-group">                              		
	                               	<input class="form-control" list="list_keywords" id="category" name="category" placeholder="type a new category on select one"/>
	                               	<span class="input-group-addon">Category</span> 
									<datalist id="list_keywords">
									  <c:if test="${fn:length(categoriesList) > 0}">
									  	<c:forEach var="i" begin="0" end="${fn:length(categoriesList)-1}">
									  		<option value="${categoriesList[i].name}">
									  	</c:forEach>
									  </c:if>
									</datalist>						                          
                               </div>
                               <div id="form_error_keywords" class="form-group input-group">                    
                                   <textarea class="form-control" rows="3" placeholder="keyword1,keyword2,keyword3..." id="keywords"  name="keywords" ></textarea>
                                   <span class="input-group-addon">Keywords</span>
                               </div>  
                               <div id="form_error_search_engine" class="form-group input-group">                              		
	                               	<select  class="form-control" id="search_engine" name="search_engine" placeholder="select a search engine">
									  <c:if test="${fn:length(searchEnginesList) > 0}">
			                                <c:forEach var="i" begin="0" end="${fn:length(searchEnginesList)-1}">
				                                <option value="${searchEnginesList[i].url}">${searchEnginesList[i].url}</option>
											</c:forEach>
										</c:if>
									</select>
	                               	<span class="input-group-addon">Search Engine</span> 			                          
                               </div>  
                               <div id="form_error_search_engine" class="form-group input-group">                              		
	                               	<select  class="form-control" id="search_engine" name="search_engine" placeholder="select a search engine">
									    <c:if test="${fn:length(websitesList) > 0}">
			                                <c:forEach var="i" begin="0" end="${fn:length(websitesList)-1}">
				                                <option value="${websitesList[i].name}">${websitesList[i].name}</option>
											</c:forEach>
										</c:if>
									</select>
	                               	<span class="input-group-addon">Import Keywords from Website</span> 	                          
                               </div>  
                               
                               <div><H4 id="form_error_message"></H4></div>            
                               
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>
                    
                    </form>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            
        </div>
        <!-- /.col-lg-12 -->
    </div>
    
        <script>
    $(document).ready(function() {      
    	
    	// If URL contain "#addWebsite" THEN AddWebsite dialog is showed
        if(window.location.href.indexOf("#addWebsite",0) > 0) $('#modal_add_site').modal('show');  
    	
    	// Datatable config
    	$('#datatable-websites').DataTable({
                responsive: true
        }); 
    	
    	// Submit Form in AJAX
    	var frm = $('#form_add_website');
        frm.submit(function (ev) {
            $.ajax({
                type: frm.attr('method'),
                url: frm.attr('action'),
                data: frm.serialize(),
                success: function (data) {
                	
                	alert(data.success);
                	
                	if (data.success == false)
                	{
                		if (data.nameError) $("#form_error_name").addClass("has-error");
                		if (data.urlError) $("#form_error_url").addClass("has-error");
                		if (data.categoryError) $("#form_error_category").addClass("has-error");
                		if (data.keywordsError) $("#form_error_keywords").addClass("has-error");
                		$("#form_error_message").html(data.message);
                	}                  
                    
                },
                error: function (xhr, ajaxOptions, thrownError) {
	                   alert("AJAX error : " + xhr.status + thrownError);
                }
            });

            ev.preventDefault();
        });

    });
    
    function initAddForm()
    {
    	$("#form_error_name").removeClass("has-error");
		$("#form_error_url").removeClass("has-error");
		$("#form_error_category").removeClass("has-error");
		$("#form_error_keywords").removeClass("has-error");
		$("#form_error_message").html("");
    }
    
    function openAddForm()
    {
    	initAddForm();
    	$('#modal_add_site').modal('show');
    }
    
    </script>
         
   