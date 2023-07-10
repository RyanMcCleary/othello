const boardSize = 8;
const board = document.getElementById('board');

for (let i = 0; i < boardSize; i++) {
    const row = board.insertRow();
    for (let j = 0; j < boardSize; j++) {
        const cell = row.insertCell();
        cell.setAttribute("tabIndex", "0");
        if ((i === 3 && j === 3) || (i === 4 && j === 4)) {
            cell.classList.add('occupied', 'white');
            cell.style.backgroundColor = 'green';
            cell.ariaLabel = "white";
        }
        else if ((i === 3 && j === 4) || (i === 4 && j === 3)) {
            cell.classList.add('occupied', 'black');
            cell.style.backgroundColor = 'green';
            cell.ariaLabel = "black";
        }
        else {
            cell.onclick = () => play(cell);
        }
    }
}

let currentPlayer = 'black';
document.getElementById("currentplayer").innerHTML = "Black to Go";

function play(cell) {
    if (cell.classList.contains('black') || cell.classList.contains('white')) {
        return;
    }
    cell.classList.add('occupied', currentPlayer);
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