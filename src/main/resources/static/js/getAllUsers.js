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

        const h4 = document.createElement('h4');
        h4.textContent = user.username;

        const userIdText = document.createElement('p');
        userIdText.textContent = `User Id: ${user.userId}`;

        const totalTimePlayedText = document.createElement('p');
        totalTimePlayedText.textContent = `Total Time Played: ${user.totalTimePlayed}`;

        const freeTimeText = document.createElement('p');
        freeTimeText.textContent = `Free time for games: ${user.freeTime}`;

        const timeRemainingText = document.createElement('p');
        timeRemainingText.textContent = `Time left available to play games: ${user.timeRemaining}`;

        // const sessionListText = document.createElement('p');
        // console.log(user.gameSessions);
        // let gameSessionArray = user.gameSessions.toArray();
        // sessionListText.textContent = `Games played: ${gameSessionArray} ...`;

        placeholder.appendChild(container)
        container.appendChild(card);
        card.appendChild(h4);
        card.appendChild(userIdText);
        //card.appendChild(sessionListText);
        card.appendChild(totalTimePlayedText);
        card.appendChild(freeTimeText);
        card.appendChild(timeRemainingText);
    })
}

