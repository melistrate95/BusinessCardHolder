$ ->
  $('#prev-button').click ->
    $('#myCarousel').Carousel3d('prev')
  $('#next-button').click ->
    $('#myCarousel').Carousel3d('next')

  $('#removeCard').click ->
    id = $(@).attr('id-card')
    $.ajax
      type: 'DELETE'
      url: "cards/delete" + '?' + $.param idCard: id
      dataType: 'json'
      async: false
      error: () ->
        console.log("WOOPS")
      success: (response) ->
        console.log("SUCCESS " + id)
    true