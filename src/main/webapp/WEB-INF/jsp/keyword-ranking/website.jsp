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
        <h4>Results table</h4>
        <!--  <table class="table table-striped table-bordered table-hover">-->
        <table class="table table-striped table-bordered table-hover table_runs">
        	
        	<%-- Convert returned values in arrays --%>
        	<c:set var="runs_positions" scope="session" value="${runs[2].positions.toArray()}"/>
        	<c:set var="positions" scope="session" value="${positions_array.toArray()}"/>
        	
       		<c:choose>
       			<c:when test="${fn:length(positions[0]) > 0}">
                  	
                  	<!-- Year Row -->
					<tr class="odd gradeX">
                  	<td style="width: 150px;overflow: hidden;">Year</td>
                  	<c:set var="colspan" scope="session" value="1"/>
                  	<c:forEach var="i" begin="1" end="${fn:length(positions[0])-1}">
                  		<c:if test="${i < fn:length(positions[0])-1}">
         	          		<fmt:formatDate value="${positions[0][i]}" var="fmt_year1" type="date" pattern="yyyy" />
         	          		<fmt:formatDate value="${positions[0][i+1]}" var="fmt_year2" type="date" pattern="yyyy" />
         	          		<c:choose>
	                   		 	<c:when test="${fmt_year1 == fmt_year2}">                   		 		
	                   				<c:set var="colspan" value="${colspan+1}"/>
	                   		 	</c:when>
	                   		 	<c:otherwise>
	                   		 		<td style="text-align:center;" colspan="${colspan}">${fmt_year1}</td> 
	                   		 		<c:set var="colspan" value="1"/>
	                   		 	</c:otherwise>
                   			</c:choose>
                   		</c:if>
					</c:forEach>
					<td style="text-align:center;" colspan="${colspan}">${fmt_year1}</td>
					</tr>
					
					
					<!-- Month Row -->
					<tr class="odd gradeX">
					<td>Month</td>
					<c:set var="colspan" value="1"/>
                  	<c:forEach var="i" begin="1" end="${fn:length(positions[0])-1}">
                   		<c:if test="${i < fn:length(positions[0])-1}">
         	          		<fmt:formatDate value="${positions[0][i]}" var="fmt_date1" type="date" pattern="yyyy.MM" />
         	          		<fmt:formatDate value="${positions[0][i+1]}" var="fmt_date2" type="date" pattern="yyyy.MM" />
         	          		<fmt:formatDate value="${positions[0][i]}" var="fmt_date" type="date" pattern="MM" />
         	          		<c:choose>
	                   		 	<c:when test="${fmt_date1 == fmt_date2}">                   		 		
	                   				<c:set var="colspan" value="${colspan+1}"/>
	                   		 	</c:when>
	                   		 	<c:otherwise>
	                   		 		<td style="text-align:center;" colspan="${colspan}">${fmt_date}</td> 
	                   		 		<c:set var="colspan" value="1"/>
	                   		 	</c:otherwise>
                   			</c:choose>
                   		</c:if> 
                   		
					</c:forEach>
					<td style="text-align:center;" colspan="${colspan}">${fmt_date}</td> 
					</tr>
					
					<!-- Day Row -->
					<tr class="odd gradeX">
					<td>Day</td>
                  	<c:forEach var="i" begin="1" end="${fn:length(positions[0])-1}">
                   		
                   		<td  width="20"><a href="#" onclick="deleteRun(${notes[i].id})" title="Delete this run ?"><fmt:formatDate type="date" pattern="dd" value="${positions[0][i]}"/></a></td> 
                   		
					</c:forEach>
					</tr>
					
					<!-- PR Row -->
					<tr class="odd gradeX">
					<td>${positions[1][0]}</td>
                  	<c:forEach var="i" begin="1" end="${fn:length(positions[1])-1}">
                   		
                   		<td>${positions[1][i]}</td> 
                   		
					</c:forEach>
					</tr>
					
					<!-- IndexedPages Row -->
					<tr>
					<td>${positions[2][0]}</td>
                  	<c:forEach var="i" begin="1" end="${fn:length(positions[2])-1}">
                   		
                   		<td style="font-size:9px;">${positions[2][i]}</td> 
                   		
					</c:forEach>
					</tr>
					
					
					<!-- Keywords Rows --> 
					<c:forEach var="i" begin="3" end="${fn:length(positions)-1}">
						<c:set var="position_array" scope="session" value="${positions[i]}"/>
						<tr>
							<td><b>${position_array[0]}</b></td>
							<c:forEach var="j" begin="1" end="${fn:length(position_array)-1}">
								
								<%--<td title="${position_array[j].url}">${position_array[j].pos}</td>--%>
								<td title="${not empty position_array[j].url ? position_array[j].url : ''}">${not empty position_array[j].pos ? position_array[j].pos : ''}</td>
								
							</c:forEach>
						</tr>
					</c:forEach>
					
					
				</c:when>
				<c:otherwise>No results found</c:otherwise>
			</c:choose>
        
        </table>
        
	    <form role="form" id="form_launch_run" method="POST" action="<c:url value="/keyword-ranking/launchRun"/>">         	
	    	<a class="btn btn-primary" onclick="launchRunModal(${site.id})" role="button">Launch run</a>
	    </form>
        
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

<!-- MODAL DELETE RUN -->           
<div style="display: none;" class="modal fade" id="modal_delete_run" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_delete_run" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <form role="form" id="form_delete_run" method="POST" action="<c:url value="/keyword-ranking/deleteRun"/>">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel_delete_run">Delete run</h4>
            </div>
            <div class="modal-body">Are you sure to delete this run ?</div>
            
            <input id="delete_run_id" name="delete_run_id" type="hidden"/>
                               	
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

<!-- MODAL LAUNCH RUN -->           
<div style="display: none;" class="modal fade" id="modal_launch_run" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_launch_run" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel_launch_run">Launch Run - ${site.name}</h4>
            </div>
            
           <div id="modal_launch_run_text" class="modal-body">
               <p>
                   <strong><span>Run in progress...</span></strong>
               </p>
               <div class="progress progress-striped active">
                   <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                       
                   </div>
               </div>
           </div>
                               	
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.reload();">Close</button>
            </div>
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
    	
    	// Delete Run AJAX
    	var frm_delete_run = $('#form_delete_run');
    	frm_delete_run.submit(function (ev) {
            $.ajax({
                type: frm_delete_run.attr('method'),
                url: frm_delete_run.attr('action'),
                data: frm_delete_run.serialize(),
                success: function (data) {
                	
                	if (data.success == false)
                	{
                		$('#modal_delete_run').modal('hide'); 
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
    
    // DELETE RUN Modal Form
    function deleteRun(id)
    {
    	$('#delete_run_id').val(id);
    	$('#modal_delete_run').modal('show');   	
    }
    
    // OPEN MODAL FORM Add Run
    function launchRunModal(id)
    {
    	// Show the modal window
    	$('#modal_launch_run').modal('show');
    	
    	// AJAX Request to calculate run
    	var frm_launch_run = $('#form_launch_run');
    	$.ajax({
            method: "POST",
            url: frm_launch_run.attr('action'),
            data: "id=" + id,
            dataType: "json",
            success: function (run) {
         		            	
            	$('#modal_launch_run_text').html("<strong><span>Results :</span></strong><br>");
            	$('#modal_launch_run_text').append("<div>PR : <b>" + run.pr + "</b><br>");
           		$('#modal_launch_run_text').append("Indexed pages : <b>" + run.indexedPages + "</b><br>");
           		
           		for(var i=0;i<run.positions.length; i++){
           			
           			var pos = run.positions[i];
           			$('#modal_launch_run_text').append(pos.keyword.name + " : <b>" + pos.pos + "</b> (" + pos.url + ")<br>");

           		}
           		
           		$('#modal_launch_run_text').append("</div>");
            		            		
            	
            },
            error: function (xhr, ajaxOptions, thrownError) {
                   alert("AJAX error : " + xhr.status + thrownError);
            }
        });
    	
    }
    
    	
</script>