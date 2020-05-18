let shutdown = document.querySelector("#appShutDown");
const REQ = new XMLHttpRequest();

function postShutDown() {

    REQ.open('POST', '/shutdownAppContext');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.onload = () => {
        if (REQ.status === 201 && REQ.readyState === 4) {
            console.log(REQ);
            console.log(REQ.response);
            window.alert("The app is shutting down.");
        } else {
            console.log(REQ);
            console.log(REQ.response);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
        }
    }
    REQ.send();
}

shutdown.addEventListener('click', function (event){
    event.preventDefault();
    window.alert("The app will now shut down.");
    postShutDown();
})