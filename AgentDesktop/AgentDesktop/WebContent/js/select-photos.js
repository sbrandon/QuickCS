$(function () {
    
    var dropped = 0;
    var photos = [];

    function updateCount(){
        var countString = " Photographs Selected";
        $('#dropped-count').html(dropped + countString);
    }

    $('#start-build').click(function(){
        $.redirect('virtual-swatch-book.php', photos);
    });

    $("#user-photos img").draggable({
        revert: "invalid",
        refreshPositions: true,
        drag: function (event, ui) {
            ui.helper.addClass("draggable");
        },
        stop: function (event, ui) {
            ui.helper.removeClass("draggable");
            var image = this.src;
            if ($.ui.ddmanager.drop(ui.helper.data("draggable"), event)) {
               /*
                    When user clicks an image count is increased this should not happen
                    sb - 04/03/15
               */
                if(dropped < 32){
                    dropped++;
                    updateCount();
                    photos.push(image);
                }
                else{
                    $("#user-photos img").draggable( "disable" );
                }
            }
        }
    });
    $("#book-photos").droppable({
        drop: function (event, ui) {
            if ($("#book-photos img").length == 0) {
                $("#book-photos").html("");
            }
            ui.draggable.addClass("dropped");
            $("#book-photos").append(ui.draggable);
        }
    });
});