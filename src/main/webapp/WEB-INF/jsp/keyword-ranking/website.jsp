<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- TITLE -->
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">${site.name} (${site.url}) - Keyword Ranking</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>

<!-- RUNS TABLE -->
<div class="row">
    <div class="col-lg-12">
        Results table
    </div>
    <!-- /.col-lg-12 -->
</div>

<!-- NOTES TABLE -->
<div class="row">
    <div class="col-lg-12">
    	<h3  class="page-header">Notes</h3>
        <table class="table table-striped table-hover" id="datatable-notes">
           <thead>
               <tr>
                   <th width="100">Date</th>
                   <th width="20"></th>
                   <th>Description</th>
                   
               </tr>
           </thead>
           
           <tbody>
           		<c:set var="notes" scope="session" value="${site.notes.toArray()}"/>
           		<c:if test="${fn:length(notes) > 0}">
                    <c:forEach var="i" begin="0" end="${fn:length(notes)-1}">
                     <tr class="odd gradeX">
                         <td><fmt:formatDate type="date" value="${notes[i].date}"/></td> 
                         <td>
                         	<a href="#" onclick="deleteNote(${notes[i].id})"><i class="fa fa-trash-o fa-fw"></i></a>
                         </td>
                         <td>${notes[i].description}</td> 
                     </tr>
					</c:forEach>
				</c:if>
           </tbody>
           
        </table>
        <br>
        <form role="form" id="form_add_note" method="POST" action="<c:url value="/keyword-ranking/addNote"/>">     
        	<textarea id="text_add_note" name="text_add_note" rows="4"></textarea>
        	<div style="padding-top:8px;"><button type="submit" class="btn btn-primary">Add note</button></div>
        	<input id="site_id" name="site_id" type="hidden" value="${site.id}"/>
        </form>
        
           
    </div>
    <!-- /.col-lg-12 -->
</div>


<!-- MODAL DELETE NOTE -->           
<div style="display: none;" class="modal fade" id="modal_delete_note" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_delete_note" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <form role="form" id="form_delete_note" method="POST" action="<c:url value="/keyword-ranking/deleteNote"/>">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel_delete_note">Delete note</h4>
            </div>
            <div class="modal-body">Are you sure to delete this note ?</div>
            
            <input id="delete_note_id" name="delete_note_id" type="hidden"/>
                               	
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

<!-- JAVASCRIPTS -->
<script>
    $(document).ready(function() {      
    	
    	// Datatable config
    	$('#datatable-notes').DataTable({
                responsive: true
        });
    	
    	// Delete Note AJAX
    	var frm_delete_note = $('#form_delete_note');
    	frm_delete_note.submit(function (ev) {
            $.ajax({
                type: frm_delete_note.attr('method'),
                url: frm_delete_note.attr('action'),
                data: frm_delete_note.serialize(),
                success: function (data) {
                	
                	if (data.success == false)
                	{
                		$('#modal_delete_note').modal('hide'); 
                		MessageBox("KNI Seo Tools",data.message);              		
                	} else {
                		
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
    	
    });
    
    // DELETE NOTE Modal Form
    function deleteNote(id)
    {
    	$('#delete_note_id').val(id);
    	$('#modal_delete_note').modal('show');   	
    }
    
    	
</script>