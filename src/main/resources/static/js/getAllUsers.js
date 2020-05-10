let display = document.querySelector("#getAllUsersResponse");
const container = document.createElement('div')
container.setAttribute('class', 'container')

const REQ = new XMLHttpRequest();

function getAllUsers() {
    REQ.onload = () => {
        if (REQ.status === 200 && REQ.readyState === 4) {
            console.log(REQ);
            console.log(REQ.response);
            buildDisplay(display, REQ.response);
            console.log("The request for data has been sent.");
        } else {
            console.log(REQ);
            console.log(REQ.response);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
        }
    }
    REQ.open('GET', '/getAllUsers');
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.responseType = 'json';
    REQ.send();
}

window.addEventListener('load', getAllUsers);

function buildDisplay(placeholder, data){
    data.forEach(user => {
        const card = document.createElement('div');
        card.setAttribute('user', 'card');

        const h1 = document.createElement('h1');
        h1.textContent = user.username;

        const userIdText = document.createElement('p');
        userIdText.textContent = `User Id: ${user.userId}`;

        // const totalTimePlayed = document.createElement('p');
        // totalTimePlayed.textContent = `Total Time Played: ${user.totalTimePlayed}`;
        //
        // const freeTime = document.createElement('p');
        // freeTime.textContent = `Free time for games: ${user.freeTime}`;
        //
        // const timeRemaining = document.createElement('p');
        // timeRemaining.textContent = `Time left available to play games: ${user.timeRemaining}`;

        const sessionList = document.createElement('p');
        user.gameSessions = user.gameSessions.substring(0, 300);
        sessionList.textContent = `Games played: ${user.gameSessions}...`;

        placeholder.appendChild(container)
        container.appendChild(card);
        card.appendChild(h1);
        card.appendChild(userIdText);
        card.appendChild(sessionList);
        // card.appendChild(totalTimePlayed);
        // card.appendChild(freeTime);
        // card.appendChild(timeRemaining);
    })
}

