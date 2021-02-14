function modalopen(){
    const hasParam = window.location.href.indexOf('login');

    if(hasParam) {
        $('#loginModal').show();
    } else {
        $('#loginModal').hide();
    }
}
