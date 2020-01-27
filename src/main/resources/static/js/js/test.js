let stock;
let stockArrayBy80 = [];

function loadAllStockfromHost() {
    const url = window.location.host;

    let filtered = [];
    // console.log(inid );
    $.get("http://" + url + "/test/api/all/", function (data) {
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
    const url = window.location.href;

    $.get(url + "/api/" + valSelected, function (data, status) {


        let result = '<div id="' + valSelected + '" class="card-' + valSelected + '">' + data + '</div>';
        // let div = document.createElement("div").className = valSelected;
        let element = document.createElement("div");
        element.id = valSelected;
        element.innerHTML = result;
        document.getElementById("newSelection").appendChild(element);
    });

}

function getStockAllPriceGoogle(data) {


    const url = window.location.href;
    data.forEach(function (valSelected) {
        let name = valSelected.name

        $.get(url + "/api/" + valSelected.name, function (data, status) {


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
            temp_list =[];
        }
    }
    stockArrayBy80.push(temp_list);

    // for (let i = 0; i <stockArrayBy80.length ; i++) {
    //     setTimeout(function(){
    //         alert(stockArrayBy80[i]);
    //     }, 3000);
    // }

}