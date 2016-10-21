/**
 * Created by murad on 7/23/16.
 */

$(document).ready(function(){
    $('#templateSelectbox').on('change', function() {
        //alert( this.value ); // or $(this).val()
        $.get( "/ajax/templates?id="+$(this).val(), function( data ) {
            console.log(data);

            $('#subject').val(data.title);
           // $('#content').val(data.text);

            CKEDITOR.instances['content'].setData(data.text);
        });
    });

});

