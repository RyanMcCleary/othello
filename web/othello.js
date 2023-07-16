const boardSize = 8;
const board = document.getElementById('board');

for (let i = 0; i < boardSize; i++) {
    const row = board.insertRow();
    for (let j = 0; j < boardSize; j++) {
        const cell = row.insertCell();
        cell.setAttribute("tabIndex", "0");
        if ((i === 3 && j === 3) || (i === 4 && j === 4)) {
            cell.innerHTML=`<svg height="100" width="100">
            <circle cx="50" cy="50" r="45" stroke="white" stroke-width="3" fill="white" />
          </svg>`;
            /*cell.classList.add('occupied', 'white');*/
            cell.style.backgroundColor = 'green';
            cell.setAttribute('title', 'white');
        }
        else if ((i === 3 && j === 4) || (i === 4 && j === 3)) {
            /*            cell.classList.add('occupied', 'black');*/
            cell.innerHTML=`<svg height="100" width="100">
            <circle cx="50" cy="50" r="45" stroke="black" stroke-width="3" fill="black" />
            </svg>`;
            cell.setAttribute('title', 'black');
            cell.style.backgroundColor = 'green';
        }
        else {
            cell.onclick = () => play(cell);
        }
    }
}

let currentPlayer = 'black';
document.getElementById("currentplayer").innerHTML = "Black to Go";

function play(cell) {
    if (cell.title === "black" || cell.title === "white") {
        return;
    }
    /*cell.classList.add('occupied', currentPlayer);*/
    cell.innerHTML=`<svg height="100" width="100">
            <circle cx="50" cy="50" r="45" stroke=${currentPlayer} stroke-width="3" fill=${currentPlayer} />
            </svg>`;
 cell.setAttribute('title', currentPlayer);
    cell.style.backgroundColor = 'green';
    if (currentPlayer === 'black') {
        currentPlayer = 'white';
        document.getElementById("currentplayer").innerHTML = "White to Go";
    }
    else {
        currentPlayer = 'black';
        document.getElementById("currentplayer").innerHTML = "Black to Go";
    }
}