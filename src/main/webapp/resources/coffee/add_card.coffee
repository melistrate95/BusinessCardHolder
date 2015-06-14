$ ->
  $('.contact').draggable
    containment: '.row'
    cursor: 'move'
    revert: true
    helper: 'clone'

  $('.card').droppable
    accept: '.contact'
    hoverClass: 'hovered'
    drop: handleDropContactEvent

  $('#deleteBtn').click ->
    removeCardContact()
    $('.properties').hide()

  $('.card').dblclick ->
    $('.properties').hide()
    clearElement()

  $( '*' ).keydown (event) ->
    if event.which is 46 or event.which is 27
      $('.properties').hide()
      removeCardContact()

    if event.which == 13
      setProperties()
      clearElement()

  $('#jobs-link').click ->
    $('#contacts').hide()
    $('#contacts-link').parent().removeClass 'active'
    $('#jobs').show('fast')
    $('#jobs-link').parent().addClass 'active'
    false

  $('#contacts-link').click ->
    $('#jobs').hide()
    $('#jobs-link').parent().removeClass 'active'
    $('#contacts').show('fast')
    $('#contacts-link').parent().addClass 'active'
    false

  $("#openModal").click ->
    $('.properties').hide()
    clearElement()
    $('#myModal').modal()

  $("#uploadPdfBtn").click ->
    $('#mySpiner').modal()

  $('#mySpiner').bind "ajaxSend", ->
    $(@).modal 'show'
  .bind "ajaxComplete", ->
    $(@).modal 'hide'

  $("#submitBtn").click ->
    $('#myModal').modal('hide')
    json =
      "name" : $("#nameCard").val()
      "contactCards": []
    for contact in $('.contactCards')
      addContact(contact, json.contactCards)
    $.ajax
      type: "POST"
      url: "/saveCard"
      dataType: 'json'
      data: JSON.stringify(json)
      encoding:"UTF-8"
      contentType: "application/json; charset=UTF-8"
      mimeType: 'application/json'
      error: () ->
        console.log("WOOPS")
      success: (response) ->
        console.log ("SUCCESS idCard: " + response.id)
        html2canvas $(".card"), onrendered: (canvas) ->
          imageData = canvas.toDataURL()
          $.ajax
            url: '/saveCard/saveCardImage'
            data:
              idCard: response.id
              image: imageData
            type: 'POST'
            dataType: 'json'
            timeout: 10000
            error: () ->
              console.log("WOOPS")
            success: (response) ->
              console.log("SUCCESS url: " + response.url)
    $("#openModal").attr 'disabled' : 'disabled'
    false

  $('#textContacts').on 'input', ->
   setProperties()
  $('#fontSize').on 'input', ->
   setProperties()
  $('#fontColor').on 'input', ->
   setProperties()
  $('#backgroundColor').on 'input', ->
   setProperties()
  $('#width').on 'input', ->
   setProperties()
  $('#height').on 'input', ->
   setProperties()
  $('#positionX').on 'input', ->
   setProperties()
  $('#positionY').on 'input', ->
   setProperties()

  $('#contacts').hide()
  $('.properties').hide()

handleDropContactEvent = (event, ui) ->
  $contact = $('<div>').attr class: 'contactCards'
  $contact.text ui.draggable.text()
  $contact.css position: 'absolute'
  $('.card').append $contact
  $contact.position
    my: "center"
    at: "center"
    of: ".card"
  $contact.draggable
    containment: '.card'
    cursor: 'move'
  $contact.click ->
    clearElement()
    $('.properties').show 'fast'
    $(@).addClass 'edit'
    $(@).resizable
      resize: (event, ui) ->
        getSize ui
    $(this).draggable
      containment: '.card'
      cursor: 'move'
      drag: (event, ui) ->
        getPosition ui.helper
    getProperties $(@)

removeCardContact = () ->
  $('.edit').remove()

clearElement = () ->
  $('.edit').resizable "destroy"
  $('.card > *').removeClass 'edit'

getSize = (ui) ->
  $('#width').val ui.size.width
  $('#height').val ui.size.height

getPosition = (ui) ->
  $('#positionX').val ui.position().left
  $('#positionY').val ui.position().top

getProperties = (ui) ->
  $('#textContacts').val ui[0].innerText
  $('#fontSize').val ui.css 'font-size'
  $('#fontColor').val ui.css 'color'
  $('#backgroundColor').val ui.css 'backgroundColor'
  $('#width').val ui.outerWidth()
  $('#height').val ui.outerHeight()
  getPosition ui

setProperties = () ->
  setSize()
  setPosition()
  setText()
  setFontSize()
  setFontColor()
  setBackgroundColor()

setSize = () ->
  maxW = $('.card').outerWidth(true) - $('.edit').position.left
  maxH = $('.card').outerHeight(true) - $('.edit').position.top
  minW = $('.edit').width()
  minH = $('.edit').height()
  w = parseInt $('#width').val()
  h = parseInt $('#height').val()
  w  = maxW if w > maxW
  h  = maxH if h > maxH
  w = minW if w < minW
  h = minH if h < minH
  $('.edit').css
    'width':  w
    'height': h

setPosition = () ->
  maxX = $('.card').outerWidth(true) - $('.edit').outerWidth(true)
  maxY = $('.card').outerHeight(true) - $('.edit').outerHeight(true)
  x = parseInt $('#positionX').val()
  y = parseInt $('#positionY').val()
  x =  maxX if x > maxX
  x = 0 if x < 0
  y = maxY if y > maxY
  y = 0 if y < 0
  $('.edit').parent().css position: 'relative'
  $('.edit').css
    top: y
    left: x
    position:'absolute'

setText = () ->
  $('.edit').text $('#textContacts').val()

setFontSize = () ->
  currentFontSizeNum = parseFloat $('#fontSize').val(), 10
  $('.edit').css('font-size', currentFontSizeNum)

setFontColor = () ->
  $('.edit').css 'color', $('#fontColor').val()

setBackgroundColor = () ->
  $('.edit').css 'background-color', $('#backgroundColor').val()

addContact = (ui, arr) ->
  arr.push
    "text": $(ui)[0].innerText
    "font": $(ui).css 'font-size'
    "color": $(ui).css 'color'
    "bgcolor": $(ui).css 'backgroundColor'
    "width": $(ui).outerWidth()
    "height": $(ui).outerHeight()
    "xposition": $(ui).position().left
    "yposition": $(ui).position().top