// Generated by CoffeeScript 1.9.3
(function() {
  $(function() {
    $('#prev-button').click(function() {
      return $('#myCarousel').Carousel3d('prev');
    });
    $('#next-button').click(function() {
      return $('#myCarousel').Carousel3d('next');
    });
    return $('#removeCard').click(function() {
      var id;
      id = $(this).attr('id-card');
      $.ajax({
        type: 'DELETE',
        url: "cards/delete" + '?' + $.param({
          idCard: id
        }),
        dataType: 'json',
        async: false,
        error: function() {
          return console.log("WOOPS");
        },
        success: function(response) {
          return console.log("SUCCESS " + id);
        }
      });
      return true;
    });
  });

}).call(this);
