<%@include file="_tagIncludes.jsp" %>
<%@ taglib prefix="mytag" uri="/tlds/myTld.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src='<c:url value="/resources/jquery-1.5.1.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/jquery-ui-1.8.11.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/colResizable-1.3.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/jquery.unobtrusive-ajax.min.js" />' ></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/themes/base/jquery.ui.all.css" />' />
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/Grid.css" />' />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready( function(){
    $("table").colResizable();
});

function Del(_o)
{     
            $("<div><div style='display:table;'><button style='height:100%;'><span class='ui-icon ui-icon-help' /></button><span style='display:table-cell'>Are you sure you want to delete this record?</span></div></div>")
                .addClass("dialog")
                .attr("id", $(_o).attr("deleteDialogId"))
                .appendTo("body")                    
                .dialog({
                    title: "Attention"
                    ,close: function () { $(this).remove() }
                    ,modal: true
                    ,width: 420
                    ,resizable: false
                    //,dialogClass: "ui-state-error"
                    ,buttons: [
                        {
                            html: "<span class='ui-icon ui-icon-circle-close' />"
                            ,title: "No"
                            ,click: function () {
                                  $(this).dialog("close");  
                            }                                
                        }
                        ,{
                            html: "<span class='ui-icon ui-icon-circle-check' />"
                            ,title: "Yes"
                            ,Class: "ui-state-error"
                            ,click: function () {
                                $.ajax({url: _o.href
                                        ,success:function(result){
                                            Search(_o);
                                            $('#' + $(_o).attr("deleteDialogId")).dialog("close");
                                            ShowError(result);
                                            
                                        }
                                        ,error:function(xhr,status,error){
                                             $('#' + $(_o).attr("deleteDialogId")).dialog("close");
                                             ShowError(error);
                                        }
                                    });                                    
 
                            }
                        }
                    ]                        
                });
                
    return false;
}                    

function Search(_o){
    var tempClosetForm = $(_o).closest('form');
    $.ajax({
    		type: 'POST',
            url: tempClosetForm.attr('Action')
            ,data: tempClosetForm.serialize()
            ,success:function(result){
                tempClosetForm.find('tbody').empty().append(result);
            }
            ,error:function(xhr,status,error){                                                 
                ShowError(error);
            }
    });
return false;
}                    
function ShowError(msg)
{
       
         $("<div><div style='display:table;'><button style='height:100%;'><span class='ui-icon ui-icon-cancel' /></button><span style='display:table-cell'>" + msg +"</span></div></div>")
                .addClass("dialog")
                .attr("id","ErorDialog")
                .appendTo("body")
                .dialog({
                    title: "Warning"
                    ,close: function () { $(this).remove() }
                    ,modal: true
                    ,resizable: false
                    ,dialogClass: "ui-state-error"                     
                    ,buttons: [
                     {
                          html: '<span class="ui-icon ui-icon-circle-close" />'
                          ,title: "Close"                              
                          ,click: function () {
                                $(this).dialog("close");
                            }
                        }
                    ]
                });            
}
function AddEdit(_o)
{       

         $("<div></div>")
                .addClass("dialog")
                .attr("id", $(_o).attr("addEditDialogId"))
                .attr("callerobjectID", $(_o).closest('form').attr("id"))
                .appendTo("body")
                .dialog({
                    title: $(this).attr("data-dialog-title")
                    ,close: function () { $(this).remove() }
                    ,modal: true
                    ,width: 700
                    ,height: 500
                    ,buttons: [                           
                        {
                            html: '<span class="ui-icon ui-icon-circle-close" />'
                            ,title: "Cancel"
                            ,click: function () {
                                $(this).dialog("close");
                            }
                        }
                        ,{
                            html: '<span class="ui-icon ui-icon-disk" />'
                            ,title: "Save"
                            ,Class: "ui-state-error"
                            ,click: function () {
                                        var tempClosetForm =$(this).find("form[Action$='Save']");
                                        if (tempClosetForm.length != 0)
                                        {
                                            $.ajax({
                                                url: tempClosetForm.attr('action')                                                    
                                                ,data: tempClosetForm.serialize()
                                                ,success:function(result){
                                                    var thisDialog = $('#' + $(_o).attr("addEditDialogId"));
                                                    Search($('#' + thisDialog.attr("callerobjectID")));
                                                    thisDialog.dialog().html(result);
                                                }
                                                ,error:function(xhr,status,error){
                                                    //$('#' + $(_o).attr("data-dialog-id")).dialog("close");
                                                    ShowError(error);
                                                }
                                            });
                                        }
                                        else
                                             $(this).dialog("close");        
                              }
                            
                        }
                    ]
                })
                .load(_o.href,SetDatePicker);
                
    return false;
}

function SetDatePicker()
{
    $(".datePicker").datepicker({ showOn: 'both', buttonImage: "ui-icon-calculator" });   
}

</script>
</head>
<body>
<mytag:helloWorld entity="${entity}" controllerPath="roles" method="edit"/>
</body>
</html>

