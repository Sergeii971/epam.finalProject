$('#button').click(function(){
    $("input[type='file']").trigger('click');
})

function $(inputTypeFile) {

}
$("input[type='file']").change(function(){
    $('#val').text(this.value)
})
