<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!-- Modal -->           
	<div style="display: none;" class="modal fade" id="modal_message_box" tabindex="-1" role="dialog" aria-labelledby="messagebox_title" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">	
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h4 class="modal-title" id="messagebox_title"></h4>
	            </div>
	            <div class="modal-body" id="messagebox_message">   
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

function MessageBox(title,message)
{
	// Write parameters
	$("#messagebox_title").html(title);
	$("#messagebox_message").html(message);
	 	
	// Show in a modal mode
	$('#modal_message_box').modal('show');
}

</script>