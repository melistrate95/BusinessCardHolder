$ ->
  $('#searchBtn').click ->
    $(@).attr('href', '/search?' + $.param searchText: $('#searchText').val())
    true
