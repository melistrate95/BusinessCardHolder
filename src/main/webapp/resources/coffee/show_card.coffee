$ ->
  $('.card').hide()
  $.ajax
    type: "GET"
    url: "json"
    encoding:"UTF-8"
    contentType: "application/json; charset=UTF-8"
    dataType: 'json'
    success: (json, headers, status) ->
      col = json.contactCards.length
      $('.card').show('slow');
      $('.card').css
        position: 'relative'
      for i in [0..col]
        $contact = $('<div>').attr
          'class': 'contactCards'
        $contact.text json.contactCards[i].text
        $('.card').append $contact
        $contact.css
          position: 'absolute'
          top: json.contactCards[i].yposition
          left: json.contactCards[i].xposition
          'font-size': json.contactCards[i].font
          color: json.contactCards[i].color
          'background-color': json.contactCards[i].bgcolor
          width: json.contactCards[i].width
          height: json.contactCards[i].height
    error: (data, headers, status) ->
      alert("error!!!");
