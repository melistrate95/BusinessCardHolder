$(function() {
    $( '.contact' ).draggable({
        containment: '.row',
        cursor: 'move',
        revert: true,
        helper: 'clone'
    });
    $( '.card' ).droppable({
        accept: '.contact',
        hoverClass: 'hovered',
        drop: handleDropContactEvent
    });
    $( '.basket' ).droppable({
        accept: '.contactCards',
        hoverClass: 'hovered',
        drop: handleDropContactCardsEvent
    });
    $( '*' ).keydown(function( event ) {
        if ( event.which == 27 ) {
            $(clearElement());
        }
        if ( event.which == 13 ) {
            $(setProperties());
            $(clearElement());
        }
    });

    $( "#submitBtn" ).click(function() {
        var json = { "name" : $("#nameCard").val(), "contactCards": [] },
            col = $('.contactCards').length;
        for( var i = 0; i < col; i++) {
            addContact($('.contactCards')[i], json.contactCards);
        }
        $.ajax({
            type: "POST",
            url: "/saveCard",
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function(data, headers, status) {
                alert("success" + data);
            },
            error: function(data, headers, status) {
                alert("error" + data);
            }
        });
        return false;
    });

    $( '.properties' ).hide();
    $( '.basket' ).hide();
});

function handleDropContactEvent( event, ui ) {
    var $contact = $( '<div>' ).attr({ 'class': 'contactCards' });
    $contact.text( ui.draggable.text() );
    $contact.css( 'position', 'absolute' );
    $( '.card' ).append( $contact );
    $contact.position({ my: "center", at: "center", of: ".card" });
    $contact.draggable({ containment: '.card', cursor: 'move' });
    $contact.dblclick(function() {
        $(clearElement());
        $( '.properties' ).show( 'slow' );
        $( '.basket' ).show( 'slow' );
        $(this).addClass( 'edit' );
        $(this).resizable({
            resize: function( event, ui ) {
                getSize( ui );
            }
        });
        $(this).draggable({
            containment: '.work-space',
            cursor: 'move',
            drag: function( event, ui ) {
                getPosition( ui.helper );
            }
        });
        getProperties( $(this) );
    });
};

function handleDropContactCardsEvent( event, ui ) {
    if ( ui.draggable.hasClass( 'edit' ) )
        $(clearElement());
    ui.draggable.remove();
    $( '.basket' ).hide( 'fast' );
};

function clearElement() {
    $('.properties').hide('fast');
    $('.basket').hide('fast');
    $('.edit').resizable("destroy");
    $('.edit').draggable( "option", "containment", ".card");
    $('.card > *').removeClass('edit');
    $(".edit").css({position:'absolute'});
};

function getProperties( ui ) {
    $( '#textContacts' ).val( ui[0].innerText );
    $( '#fontSize' ).val( ui.css( 'font-size' ) );
    $( '#fontColor' ).val( ui.css( 'color' ) );
    $( '#backgroundColor' ).val( ui.css( 'backgroundColor' ) );
    $( '#width' ).val( ui.outerWidth() );
    $( '#height' ).val( ui.outerHeight() );
    getPosition(ui);
};

function setProperties() {
    setSize();
    setPosition();
    setText();
    setFontSize();
    setFontColor();
    setBackgroundColor();
};

function getPosition(ui) {
    $( '#positionX' ).val( ui.position().left );
    $( '#positionY' ).val( ui.position().top );
};

function setPosition(){
    var maxX = $( ".card" ).outerWidth( true ) - $( ".edit" ).outerWidth( true ),
        maxY = $( ".card" ).outerHeight( true ) - $( ".edit" ).outerHeight( true ),
        x = parseInt($( '#positionX' ).val()),
        y = parseInt($( '#positionY' ).val());
    x = x > maxX ? maxX : x;
    x = x < 0 ? 0 : x;
    y = y > maxY ? maxY : y;
    y = y < 0 ? 0 : y;
    $( ".edit" ).parent().css({ position: 'relative' });
    $( ".edit" ).css({ top: y, left: x, position:'absolute' });
};

function setFontSize() {
    var currentFontSizeNum = parseFloat( $( '#fontSize' ).val(), 10 );
    $( ".edit" ).css( 'font-size', currentFontSizeNum );
};

function setText() {
    $( ".edit").text( $( "#textContacts" ).val() );
};

function setFontColor() {
    $( ".edit" ).css( 'color',  $( '#fontColor' ).val() );
};

function setBackgroundColor() {
    $( ".edit" ).css( 'background-color',  $( '#backgroundColor' ).val() );
};

function setSize() {
    var maxW = $( ".card" ).outerWidth( true ) - $( ".edit" ).position.left,
        maxH = $( ".card" ).outerHeight( true ) - $( ".edit" ).position.top,
        minW = $( ".edit" ).width(),
        minH = $( ".edit" ).height(),
        w = parseInt($( '#width' ).val()),
        h = parseInt($( '#height' ).val());
    w = w > maxW ? maxW : w;
    h = h > maxH ? maxH : h;
    w = w < minW ? minW : w;
    h = h < minH ? minH : h;
    $( ".edit" ).css({ 'width':  w, 'height': h });
};

function getSize(ui) {
    $( '#width' ).val( ui.size.width );
    $( '#height' ).val( ui.size.height );
};



