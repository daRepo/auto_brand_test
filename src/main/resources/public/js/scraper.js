const loading = document.createElement("p");
loading.innerText = "Loading...";
loading.style.fontStyle = "italic"; 
loading.style.backgroundColor = "yellow";
loading.style.padding = "10px";
loading.style.borderRadius = "5px";

const loaded = document.createElement("p");
loaded.innerText = "Loaded";
loaded.style.fontWeight = "bold";
loaded.style.backgroundColor = "green";
loaded.style.color = "white";
loaded.style.padding = "10px";
loaded.style.borderRadius = "5px";

const error = document.createElement("p");
error.innerText = "Error";
error.style.fontWeight = "bold";
error.style.backgroundColor = "red";
error.style.color = "white";
error.style.padding = "10px";
error.style.borderRadius = "5px";

var retrievedData;

function scrap() {

    loadingStatus();
    fetch('./scraper/data')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                doneStatus();
                retrievedData = data;
                displayData(data);
            })
            .catch(error => {
                errorStatus();
                console.error('There was a problem with the fetch operation:', error);
            });
}

function loadingStatus() {

    const statusContainer = document.getElementById("status");

    statusContainer.innerText = "";
    statusContainer.appendChild(loading);
}

function doneStatus() {

    const statusContainer = document.getElementById("status");
    const downloadButton = document.getElementById("downloadButton");
    downloadButton.style.display = "block";

    statusContainer.innerText = "";
    statusContainer.appendChild(loaded);
}

function errorStatus() {

    const statusContainer = document.getElementById("status");
    const downloadButton = document.getElementById("downloadButton");
    downloadButton.style.display = "none";

    statusContainer.innerText = "";
    statusContainer.appendChild(error);
}

function displayData(v_json) {

    var container = document.getElementById("data");
    var table = document.createElement("table");

//    var parsedJson = JSON.parse(v_json);
    var parsedJson = v_json;

    var row = document.createElement("tr");

    var codArticol = document.createElement("td");
    codArticol.innerText = "Cod Articol";

    var articol = document.createElement("td");
    articol.innerText = "Articol";

    var pret = document.createElement("td");
    pret.innerText = "Pret";

    var unitate = document.createElement("td");
    unitate.innerText = "Unitate";

    var producator = document.createElement("td");
    producator.innerText = "Producator";

    var stoc = document.createElement("td");
    stoc.innerText = "Stoc / localitate";

    var id = document.createElement("td");
    id.innerText = "id";

    var pentruArticol = document.createElement("td");
    pentruArticol.innerText = "Pentru articol";

    row.appendChild(codArticol);
    row.appendChild(articol);
    row.appendChild(pret);
    row.appendChild(unitate);
    row.appendChild(producator);
    row.appendChild(stoc);
    row.appendChild(id);
    row.appendChild(pentruArticol);
    table.appendChild(row);

    for (var i = 0; i < parsedJson.length; i++) {

        var row = document.createElement("tr");
        var current = parsedJson[i];

        var codArticol = document.createElement("td");
        codArticol.innerText = current.codArticol;

        var articol = document.createElement("td");
        articol.innerText = current.articol;

        var pret = document.createElement("td");
        pret.innerText = current.pret;

        var unitate = document.createElement("td");
        unitate.innerText = current.unitate;

        var producator = document.createElement("td");
        producator.innerText = current.producator;

        var stoc = document.createElement("td");
        stoc.innerText = current.stoc;

        var id = document.createElement("td");
        id.innerText = current.id;

        var pentruArticol = document.createElement("td");
        pentruArticol.innerText = current.prentruArticol;

        row.appendChild(codArticol);
        row.appendChild(articol);
        row.appendChild(pret);
        row.appendChild(unitate);
        row.appendChild(producator);
        row.appendChild(stoc);
        row.appendChild(id);
        row.appendChild(pentruArticol);
        table.appendChild(row);
    }
    container.innerText = "";
    container.appendChild(table);
}

function downloadExcel() {

    // Create a new workbook
    var workbook = XLSX.utils.book_new();

    // Convert data to worksheet
    var worksheet = XLSX.utils.json_to_sheet(retrievedData);

    // Add the worksheet to the workbook
    XLSX.utils.book_append_sheet(workbook, worksheet, "Sheet1");

    // Generate an Excel file
    XLSX.writeFile(workbook, "Data.xlsx");
}
