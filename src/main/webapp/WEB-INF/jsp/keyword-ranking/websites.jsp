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
        <div class="col-lg-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Websites list
                    <div class="pull-right">
                    	<button class="btn btn-default btn-s" data-target="#modal_add_site" data-toggle="modal"><i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-hover" id="dataTables-example">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>URL</th>
                                    <th>Category</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="i" begin="0" end="${fn:length(websitesList)-1}">
	                                <tr class="odd gradeX">
	                                    <td>
	                                    	<a href="<c:url value="/keyword-ranking/site?id=${websitesList[i].id}"/>"><c:out value="${websitesList[i].name}"/></a> - 
	                                    	<a href="#"><i class="fa fa-pencil-square-o fa-fw"></i></a>
	                                    	<a href="#"><i class="fa fa-trash-o fa-fw"></i></a>
	                                    </td>
	                                    <td><a href="${websitesList[i].url}" target="_blank"><c:out value="${websitesList[i].url}"/></a></td>
	                                    <td><c:out value="jeux"/></td>
	                                </tr>
								</c:forEach>
                                
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
                    <form role="form" method="POST" action="<c:url value="/keyword-ranking/websites"/>">
                   
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">Add a new website</h4>
                        </div>
                        <div class="modal-body">
                                          	                                   
                               <div class="form-group input-group">     
                                   <input class="form-control" id="name" placeholder="Site name" type="text"/>
                                   <span class="input-group-addon">Name</span>
                               </div>
                               <div class="form-group input-group">     
                                   <input class="form-control" id="url" placeholder="http://" type="text"/>
                                   <span class="input-group-addon">URL</span>
                               </div>
                               <div class="form-group input-group">                              		
	                               	<input class="form-control" list="list_keywords" id="category"  placeholder="type a new category on select one"/>
	                               	<span class="input-group-addon">Category</span> 
									<datalist id="list_keywords">
									  <option value="jeux">
									  <option value="test">
									  <option value="test1">
									  <option value="test2">
									  <option value="test3">
									</datalist>						                          
                               </div>
                               <div class="form-group input-group">                    
                                   <textarea class="form-control" rows="3" placeholder="keyword1,keyword2,keyword3..." id="keywords" ></textarea>
                                   <span class="input-group-addon">Keywords</span>
                               </div>                
                               
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
            
            
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });
    </script>
   