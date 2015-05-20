/**
 * Created by Mike on 21/05/2015.
 */
$(function() {
    $('td.change').click(function() {
//                    var t = e.target || e.srcElement;
//                    var elm_name = t.tagName.toLowerCase();
//                    if(elm_name == 'input') {return false;}
        var val = $(this).html();
        var code = '<input type="text" id="edit" value="'+val+'" />';
        $(this).empty().append(code);
        $('#edit').focus();
        $('#edit').blur(function() {
            var val = $(this).val();
            $(this).parent().empty().html(val);
        });
    });
});
$(window).keydown(function(event){
    if(event.keyCode == 13) {
        $('#edit').blur();
    }
});