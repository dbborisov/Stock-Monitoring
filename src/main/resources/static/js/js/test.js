let stock;
let stockArrayBy80 = [];

function loadAllStockfromHost() {
    const url = window.location.host;

    let filtered = [];
    // console.log(inid );
    $.get("http://" + url + "/test/api/jsoup/all/", function (data) {
        let temp = [];
        // console.log(data)
        for (let i = 0; i < data.length; i++) {
            temp.push({id: data[i].id, name: data[i].name, fullName: data[i].fullName, price: data[i].price});
        }
        revolutStockList(data);
        stock = temp;
    });
}

function revolutStockList(data) {

    // console.log(url );


    data.forEach(function (dataKey) {
        let temp = document.createElement("option");
        temp.setAttribute("data-tokens", dataKey.name);
        temp.setAttribute("value", dataKey.name);
        temp.innerHTML = dataKey.fullName + " : " + dataKey.name;
        document.querySelector(".stock_picker").appendChild(temp);
    });
    // .querySelector("#"+id).innerHTML = data.response;
    // alert("Data: " + data.response + "\nStatus: " + status);

}

function getStockPriceGoogle() {

    let selected = document.querySelector("select");
    let valSelected = selected.options[selected.selectedIndex].value;
    const url = window.location.host;

    $.get("http://" + url + "/test/api/jsoup/" + valSelected, function (data, status) {


        let result = '<div id="' + valSelected + '" class="card-' + valSelected + '">' + data + '</div>';
        // let div = document.createElement("div").className = valSelected;
        let element = document.createElement("div");
        element.id = valSelected;
        element.innerHTML = result;
        document.getElementById("newSelection").appendChild(element);
    });

}

function getStockAllPriceGoogle(data) {


    const url = window.location.host;
    data.forEach(function (valSelected) {
        let name = valSelected.name

        $.get("http://" + url + "/test/api/jsoup/" + valSelected.name, function (data, status) {

            $.post("http://"+url+"/api/price/save",{name:name,price:data});
            let result = '<div id="' + name + '" class="card-' + name + '">' + data + '</div>';
            // let div = document.createElement("div").className = valSelected;
            let element = document.createElement("div");
            element.id = name;
            element.innerHTML = "<h4>" + name + "</h4>" + result;
            document.getElementById("newSelection").appendChild(element);
        });
    })
}

function spliter(data, size = 80) {
    let temp_list = [];
    for (let i = 0; i < data.length; i++) {
        temp_list.push(data[i])
        if (i % size === 0 && i !== 0) {
            console.log(i)
            stockArrayBy80.push(temp_list);
            temp_list = [];
        }
    }
    stockArrayBy80.push(temp_list);
}


    let globulCounter = 0;                     //  set your counter to 1

    function myLoop () {           //  create a loop function
        setTimeout(function () {    //  call a 3s setTimeout when the loop is called
            console.log(globulCounter);          //  your code here

            if (globulCounter < stockArrayBy80.length) {            //  if the counter < 10, call the loop function
                getStockAllPriceGoogle(stockArrayBy80[globulCounter]);
                myLoop();             //  ..  again which will trigger another
            }                        //  ..  setTimeout()
            globulCounter++;                     //  increment the counter
        }, 3000)
    }



// $.get('https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&apikey=demo',function(data){consol.log(data)})