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
                                    <th>Search Engine</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${fn:length(websitesList) > 0}">
	                                <c:forEach var="i" begin="0" end="${fn:length(websitesList)-1}">
		                                <tr class="odd gradeX">
		                                    <td>
		                                    	<a href="<c:url value="/keyword-ranking/site?id=${websitesList[i].id}"/>"><c:out value="${websitesList[i].name}"/></a> - 
		                                    	<a href="#" onclick="editWebsite(${websitesList[i].id})"><i class="fa fa-pencil-square-o fa-fw"></i></a>
		                                    	<a href="#" onclick="deleteWebsite(${websitesList[i].id},'${websitesList[i].name}','${websitesList[i].url}')"><i class="fa fa-trash-o fa-fw"></i></a>
		                                    </td>
		                                    <td><a href="${websitesList[i].url}" target="_blank"><c:out value="${websitesList[i].url}"/></a></td>
		                                    <td><c:out value="${websitesList[i].category.name}"/></td>
		                                    <td><c:out value="${websitesList[i].searchEngine.url}"/></td>
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
            <div style="display: none;" class="modal fade" id="modal_add_site" tabindex="-1" role="dialog" aria-labelledby="modal_add_site_title" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <form role="form" id="form_add_website" method="POST" action="<c:url value="/keyword-ranking/websites"/>">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="modal_add_site_title"></h4>
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
                               <div id="form_error_import_from_website" class="form-group input-group">                              		
	                               	<select  class="form-control" id="import_from_website" name="import_from_website">
									    <option value=""></option>
									    <c:if test="${fn:length(websitesList) > 0}">
			                                <c:forEach var="i" begin="0" end="${fn:length(websitesList)-1}">
				                                <option value="${websitesList[i].id}">${websitesList[i].name}</option>
											</c:forEach>
										</c:if>
									</select>
	                               	<span class="input-group-addon">Import Keywords from Website</span> 	                          
                               </div>  
                               <div id="form_error_keywords" class="form-group input-group">                    
                                   <textarea class="form-control" rows="3" placeholder="keyword1,keyword2,keyword3..." id="keywords"  name="keywords" ></textarea>
                                   <span class="input-group-addon">Keywords</span>
                               </div>  
                               <div id="form_error_search_engine" class="form-group input-group">                              		
	                               	<select  class="form-control" id="search_engine" name="search_engine">
									  <c:if test="${fn:length(searchEnginesList) > 0}">
			                                <c:forEach var="i" begin="0" end="${fn:length(searchEnginesList)-1}">
				                                <option value="${searchEnginesList[i].url}">${searchEnginesList[i].url}</option>
											</c:forEach>
										</c:if>
									</select>
	                               	<span class="input-group-addon">Search Engine</span> 			                          
                               </div>  
                               
                               <div><H4 id="form_error_message"></H4></div>            
                               
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary" id="model_add_website_button_add">Add</button>
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
    
	<!-- Modal -->           
	<div style="display: none;" class="modal fade" id="modal_delete_site" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_delete" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        <form role="form" id="form_delete_website" method="POST" action="<c:url value="/keyword-ranking/deleteWebsite"/>">
	
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h4 class="modal-title" id="myModalLabel_delete">Delete website</h4>
	            </div>
	            <div class="modal-body">
	                              	                                   
	                   Are you sure to delete this website : <span id="modal_delete_span_website"></span> ? All the runs will also be deleted !
	                   
	            </div>
	            
	            <input id="delete_site_id" name="delete_site_id" type="hidden"/>
	                               	
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	                <button type="submit" class="btn btn-primary">Delete</button>
	            </div>
	        
	        </form>
	        </div>
	        <!-- /.modal-content -->
	    </div>
	    <!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- Modal -->           
	<div style="display: none;" class="modal fade" id="modal_message_box" tabindex="-1" role="dialog" aria-labelledby="messagebox_title" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">	
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h4 class="modal-title" id="messagebox_title"></h4>
	            </div>
	            <div class="modal-body" id="messagebox_body">   
	            </div>
	                               	
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
	            </div>
	        </div>
	        <!-- /.modal-content -->
	    </div>
	    <!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
    
        <script>
    $(document).ready(function() {      
    	
    	// Datatable config
    	$('#datatable-websites').DataTable({
                responsive: true
        }); 
    	
    	// Submit Form in AJAX Add Website
    	var frm = $('#form_add_website');
        frm.submit(function (ev) {
            $.ajax({
                type: frm.attr('method'),
                url: frm.attr('action'),
                data: frm.serialize(),
                success: function (data) {
                	
                	if (data.success == false)
                	{
                		if (data.nameError) $("#form_error_name").addClass("has-error");
                		if (data.urlError) $("#form_error_url").addClass("has-error");
                		if (data.categoryError) $("#form_error_category").addClass("has-error");
                		if (data.keywordsError) $("#form_error_keywords").addClass("has-error");
                		if (data.searchEngineError) $("#form_error_search_engine").addClass("has-error");
                		$("#form_error_message").html(data.message);
                		
                	}else{
                		
                		// Reload the current page
                		location.reload();
                	}                  
                    
                },
                error: function (xhr, ajaxOptions, thrownError) {
	                   alert("AJAX error : " + xhr.status + thrownError);
                }
            });

            ev.preventDefault();
        });
        
     	// AJAX Import Keywords From Website
    	var sel_import_from_website = $('#import_from_website');
    	sel_import_from_website.change(function (ev) {
    		var siteID = $('#import_from_website').val();//sel_import_from_website.value;
    		if (sel_import_from_website.prop("selectedIndex") != 0)
    		$.ajax({
                method: "GET",
                url: "/kniseotools/keyword-ranking/importKeywords",
                data: "id=" + siteID,
                dataType: "html",
                success: function (data) {
             		$('#keywords').val(data);           		                    
                },
                error: function (xhr, ajaxOptions, thrownError) {
	                   alert("AJAX error : " + xhr.status + thrownError);
                }
            });

            ev.preventDefault();
        });
        
     	// Submit Form in AJAX Delete Website
    	var frm_delete = $('#form_delete_website');
    	frm_delete.submit(function (ev) {
            $.ajax({
                type: frm_delete.attr('method'),
                url: frm_delete.attr('action'),
                data: frm_delete.serialize(),
                success: function (data) {
                	
                	if (data.success == false)
                	{
                		frm_delete.modal('hide');
                		MessageBox("KNI Seo Tools",data.message);              		
                	}     
                	
                	// Reload the current page
            		location.reload();
                    
                },
                error: function (xhr, ajaxOptions, thrownError) {
	                   alert("AJAX error : " + xhr.status + thrownError);
                }
            });

            ev.preventDefault();
        });
    	

    });
    
    
    function deleteWebsite(id,name,url)
    {
    	$('#modal_delete_span_website').html(name + "(" + url +")");
    	$('#delete_site_id').val(id);
    	$('#modal_delete_site').modal('show');   	
    }
    
    function openAddForm()
    {
    	// Remove Error classes
    	$("#form_error_name").removeClass("has-error");
		$("#form_error_url").removeClass("has-error");
		$("#form_error_category").removeClass("has-error");
		$("#form_error_keywords").removeClass("has-error");
		$("#form_error_search_engine").removeClass("has-error");
		
		// Init fields
		$("#modal_add_site_title").html("Add website");
		$("#model_add_website_button_add").html("Add");
		$("#form_error_message").html("");
		$("#name").val("");
		$("#url").val("");
		$("#category").val("");
		$("#import_from_website").prop("selectedIndex",0);
		$("#keywords").val("");
		
    	
    	// Show the form in a modal mode
    	$('#modal_add_site').modal('show');
    }
    
    function editWebsite(id)
    {
    	// Get Site details
    	$.ajax({
            method: "GET",
            url: "/kniseotools/keyword-ranking/getSiteDetails",
            data: "id=" + id,
            dataType: "json",
            success: function (data) {
         		
            	alert(data.name);
            	
            	if (data.success == true)
            	{
            		// Fill form with site values
            		$("#form_error_message").html("");
            		$("#name").val(data.name);
            		$("#url").val(data.url);
            		$("#category").val(data.category);
            		$("#import_from_website").prop("selectedIndex",0);
            		$("#keywords").val(data.keywords);
            		
            		// Chnage name of the form
            		$("#modal_add_site_title").html("Edit website");
            		$("#model_add_website_button_add").html("Edit");
            		
            	  	// Show the form in a modal mode
                	$('#modal_add_site').modal('show');
            		            		
            	}else{
            		MessageBox("KNI Seo Tools",data.message);    
            	}
            	
            },
            error: function (xhr, ajaxOptions, thrownError) {
                   alert("AJAX error : " + xhr.status + thrownError);
            }
        });
    	
    }
    
    function MessageBox(title,message)
    {
    	// Write parameters
    	$("#messagebox_title").html(title);
    	$("#messagebox_message").html(message);
		 	
    	// Show in a modal mode
    	$('#modal_message_box').modal('show');
    }
    
    </script>
         
   