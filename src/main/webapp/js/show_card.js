$(function() {
    $( '.card' ).hide();

    $( "#showCard" ).click(function() {
        $.ajax({
            type: "GET",
            url: $("#name").text().substr() + "/json",
            dataType: 'json',
            success: function (json, headers, status) {
                var col = json.contactCards.length;
                $( '.card' ).show("slow");
                $('.card').css({ position: 'relative' });
                for (var i = 0; i < col; i++) {
                    var $contact = $('<div>').attr({ 'class': 'contactCards' });
                    $contact.text(json.contactCards[i].text);
                    $contact.css('position', 'absolute');
                    $('.card').append($contact);
                    $contact.css({ top: json.contactCards[i].yposition, left: json.contactCards[i].xposition, position: 'absolute' });
                    $contact.css('font-size', json.contactCards[i].font);
                    $contact.css('color', json.contactCards[i].color);
                    $contact.css('background-color', json.contactCards[i].bgcolor);
                    $contact.css({ 'width': json.contactCards[i].width, 'height': json.contactCards[i].height });
                }
            },
            error: function (data, headers, status) {
                alert("error!!!");
            }
        });
    });

    $( '*' ).keydown(function( event ) {
        if ( event.which == 27 ) {
            $( '.card' ).hide("slow");
        }
    });
});

