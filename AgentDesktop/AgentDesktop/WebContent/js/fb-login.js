$(document).ready(function() {
  
  $.ajaxSetup({ cache: true });
  
  $.getScript('//connect.facebook.net/en_UK/all.js', function(){
    FB.init({
      appId: '1399562950353519',
      cookie: true,
      version: 'v2.2'
    });  

    FB.getLoginStatus(function(response){
      statusChangeCallback(response);
    });

  });

  $("#login").click(function(){
    FB.login(function(response) {
      statusChangeCallback(response);
    },{scope: 'email,user_photos'});
  });

  function statusChangeCallback(response) {
    if (response.status === 'connected') {
      window.location.href = 'select-photos.php';
    } else if (response.status === 'not_authorized') {
      $("#status").text('Please log into this app.');
    } else {
      $("#status").text('Please log into facebook.');
    }
  }

});