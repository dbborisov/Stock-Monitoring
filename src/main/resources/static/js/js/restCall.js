let message;
let urls;

function revolutStockList(url) {
    const url_local = window.location.href;
    // console.log(url );

    $.get(url_local + url, function (data, status) {

        data.forEach(function (dataKey) {
            let temp = document.createElement("option");
            temp.setAttribute("data-tokens", dataKey.name);
            temp.setAttribute("value", dataKey.name);
            temp.innerHTML = dataKey.fullName + " : " + dataKey.name;
            document.querySelector(".stock_picker").appendChild(temp);
        });
        // .querySelector("#"+id).innerHTML = data.response;
        // alert("Data: " + data.response + "\nStatus: " + status);
    });
}


function reloadData(id) {
    const url = window.location.href;
    // console.log(url );

    $.get(url + "one/" + id, function (data, status) {
        let hedder = document.querySelector(".id-" + id);
        hedder.innerHTML = data.response + new Date().toLocaleTimeString();
        soundAndSum(hedder, id);
    });
}

function soundAndSum(hedder, id) {
    let currentMony = hedder.querySelector(".intraday__data .intraday__price .value");


    hedder.innerHTML += "<span>  now: " + (currentMony.textContent * 300) + (" invest :1375.7") + " now: "
        + Math.round((currentMony.textContent * 300) - 1375.7) + "</span>";
    document.querySelector("head").querySelector("title").innerText = hedder.querySelector(".company__ticker").textContent + " " + currentMony.textContent;
    let result = parseFloat(currentMony.textContent) > parseFloat(document.querySelector(".refresh-input" + id).value);
    // console.log(result)
    if (result) {
        playAudio();
        // console.log("in if")
    }
}

function reloadDataOnTempList(inid) {
    const url = window.location.href;
    // console.log(inid );
    $.get(url + "one/page/read/" + inid, function (data, status) {
        let hedder = document.querySelector(".id-" + inid);
        let currentMony = hedder.querySelector(".intraday__data .intraday__price .value");

        hedder.innerHTML = data.response + new Date().toLocaleTimeString();
        hedder.innerHTML += "<span>  now: " + (currentMony.textContent * 300) + (" invest :1375.7") + " now: "
            + Math.round((currentMony.textContent * 300) - 1375.7) + "</span>";

    });
}

function getPrice(price,range=0) {
    const url = window.location.host;
    console.log(price,range);
    let filtered = [];
    // console.log(inid );
     $.get("http://" + url + "/all/prices", function (data) {

        console.log(data)
        for (let i = 0; i < data.length; i++) {
            if (data[i].price <= price && data[i].price >= range) {
                filtered.push(data[i]);

            }
        }
        console.log(filtered);
         tempReadAndPutByPrice(filtered);
        return filtered;

    });
}

function myFunction(id) {
    const url = window.location.href;
    console.log("from get");

    return $.get(url + "one/" + id, function (data, status) {
        console.log(data)
        return data.response;
    });

    // console.log(alldata);
};

function addNewToList() {
    let selected = document.querySelector("select");
    let valSelected = selected.options[selected.selectedIndex].value;
    const url = window.location.href;
    $.get(url + "/one/page/read/" + valSelected, function (data, status) {
        let result = data.response;
        result = '<div id="' + valSelected + '" class="card-' + valSelected + '">' + result + '</div>';
        seedData(result);

    });
}

function tempReadAndPutByPrice(list) {
    // let selected = document.querySelector("select");
    // let valSelected = selected.options[selected.selectedIndex].value;
    for (let i = 0; i < list.length; i++) {
        console.log(list[i].name)
        let valSelected = list[i].name;
        const url = window.location.href;
        $.get(url + "/one/page/read/" + valSelected, function (data, status) {
            let result = data.response;
            result = '<div id="' + valSelected + '" class="card-' + valSelected + '">' + result + '</div>';
            seedData(result);

        });
    }

}

function removeElement(id) {

    // clearTimeout(("timeOut"+id));
    let selected = document.querySelector(".card.model-" + id);
    selected.remove();
    let rem = "setInterval" + id;
    console.log(rem)
    myStopFunction1(rem)
    console.log("done")


}

function myStopFunction1(some) {
    clearInterval(some);
}

function seedData(ss) {
    // let message = myFunction(ss.id);
    // console.log("from seed")
    let render = document.createElement("div");
    let messageKey = ss;


    var doc = document.createElement("div");


    var heder = document.createElement("div");

    heder.innerHTML = messageKey;
    let id = heder.querySelector("div").id
    console.log(id);
    heder.className = "card-header overflow-auto id-" + id;
    doc.className = "card  model-" + id;
    var body = document.createElement("div");
    body.className = "card-body";

    var stocName = heder.querySelector(".company__ticker").textContent;
    var p = document.createElement("p");
    p.className = "card-text";
    p.innerHTML = new Date().toLocaleTimeString();

    heder.appendChild(p);


    var a = document.createElement("a");
    a.className = "btn btn-primary";
    a.href = `https://finance.yahoo.com/quote/${stocName}/`;
    a.target = "_blank";
    a.text = "View in YahooStock";
    body.appendChild(a);

    let objectInput = document.createElement("div");
    objectInput.className = "row";
    let div1 = document.createElement("div");
    div1.className = "col-md-2";
    let div2 = document.createElement("div");

    div2.className = "col-md-2";
    let input = document.createElement("input");

    let buttInput = document.createElement("button");
    buttInput.className = "col-md-6";
    let buttInput2 = document.createElement("button");
    buttInput.className = "col-md-6";

    input.className = "refresh-input";
    input.name = id;
    input.setAttribute("size", "5")
    // console.log(input);
    buttInput.className = "refresh-input-button";
    buttInput2.className = "delete-input-button";
    buttInput.name = id;
    buttInput2.name = id;
    buttInput.textContent = "interval";
    buttInput2.textContent = "Remove";
    // buttInput.name = id;
    console.log(id);
    buttInput.setAttribute("onclick", "reloadDataOnTempList('" + id + "')");
    buttInput2.setAttribute("onclick", "removeElement('" + id + "')");
    let scripClick = document.createElement("script");
    scripClick.innerHTML =
        "let setInterval" + id + "=setInterval(function ()\n" +
        "            {\n" +

        "                if (document.getElementById('customSwitch" + id + "').checked)\n" +
        "                {\n" +
        "                    reloadDataOnTempList('" + id + "');\n" +
        "                }\n" +
        "            }, time = 3000);"
    let switcher = document.createElement("div");
    switcher.className = "custom-control custom-switch";
    switcher.innerHTML = " <input type=\"checkbox\" class=\"custom-control-input\" id=\"customSwitch" + id + "\">\n" +
        "  <label class=\"custom-control-label\" for=\"customSwitch" + id + "\">Auto Refresh in 3 sec</label>\n";


    // "<div class=\"custom-control custom-switch\">\n" +
    // "  <input type=\"checkbox\" class=\"custom-control-input\" id=\"customSwitch"+ id +"\">\n" +
    // "  <label class=\"custom-control-label\" for=\"customSwitch"+ id +"\">Auto Refresh in 3 sec</label>\n" +
    // "</div>";

    objectInput.appendChild(switcher);
    objectInput.appendChild(scripClick);
    div1.appendChild(input);
    div2.appendChild(buttInput);
    div2.appendChild(buttInput2);
    objectInput.appendChild(input);
    objectInput.appendChild(buttInput);
    objectInput.appendChild(buttInput2);
    body.appendChild(objectInput);


    doc.appendChild(heder);
    doc.appendChild(body);
    render.appendChild(doc);


    document.querySelector("#output").appendChild(render);
}


// var x = document.getElementById("myAudio");

function playAudio() {
    x.play();
}

function pauseAudio() {
    x.pause();
}

function readDataFromHost(hostData) {

    const url = window.location.href;

    for (let i = 0; i < hostData.length; i++) {
        let id = hostData[i].id;
        $.get(url + "one/page/" + id, function (data, status) {
            renderFromlist(data.response, id)
        });
    }
}

function fastCalc(element) {

    let renderElement;
    if(element.querySelector(".result-"+element.id )=== null) {
        renderElement  = document.createElement("div");
        renderElement.className = "result-" + element.id+" row";
    }else{
        renderElement=element.querySelector(".result-"+element.id)
    }
    let entryPrice = element.querySelector("#entryPrice").value;
    let stockPriceBuy = element.querySelector("#stockPriceBuy").value;
    let stockPredict = element.querySelector("#stockPredict").value;

    let stockAllBuy = Math.round((entryPrice / stockPriceBuy) * 100) / 100;
    let predictedSum = stockAllBuy * stockPredict;

    renderElement.innerHTML = "<div class='col in-card-result'><span>Predicted sum : " + predictedSum+"</span>" +
        "<p>Current outcome: " + (Math.round((predictedSum - entryPrice) * 100) / 100)+"</p></div>" +
        "<div class='col'><span> exp.p + 0.01: " + (Math.round(((stockAllBuy * (parseFloat(stockPredict) + 0.01)) - entryPrice) * 100) / 100)+"</span>" +
        "<p>Expected plus 0.05: " + (Math.round(((stockAllBuy * (parseFloat(stockPredict) + 0.05)) - entryPrice) * 100) / 100)+"</p></div>" +
        "<div class='col'><span>Expected plus 0.10: " + (Math.round(((stockAllBuy * (parseFloat(stockPredict) + 0.10)) - entryPrice) * 100) / 100)+"</span>" +
        "<p>Expected plus 0.15: " + (Math.round(((stockAllBuy * (parseFloat(stockPredict) + 0.15)) - entryPrice) * 100) / 100)+"</p></div>";

    element.querySelector("#out").appendChild(renderElement)
}

// from host
function renderFromlist(message, valueOfId) {
    // console.log(message,valueOfId);
    let render = document.createElement("div");

    var doc = document.createElement("div");
    doc.className = "card";

    var heder = document.createElement("div");

    heder.innerHTML = message;
    heder.className = "card-header overflow-auto id-" + valueOfId;
    let audio = document.createElement("audio");
    audio.className = "myAudio";
    audio.innerHTML = '<button onclick="playAudio()" type="button">Play Audio</button>\n' + '<button onclick="pauseAudio()" type="button">Pause Audio</button>';
    var body = document.createElement("div");
    heder.appendChild(audio);
    body.className = "card-body";

    var stocName = heder.querySelector(".company__ticker").textContent;
    var p = document.createElement("p");
    p.className = "card-text";
    p.innerHTML = new Date().toLocaleTimeString();


    // body.appendChild(p);
    heder.appendChild(p)


    var a = document.createElement("a");
    a.className = "btn btn-primary";
    a.href = `https://finance.yahoo.com/quote/${stocName}/`;
    a.target = "_blank";
    a.text = "View in YahooStock";
    body.appendChild(a);

    let objectInput = document.createElement("div");
    objectInput.className = "row";
    let div1 = document.createElement("div");
    div1.className = "col-md-2";
    let div2 = document.createElement("div");

    div2.className = "col-md-2";
    let input = document.createElement("input");

    let buttInput = document.createElement("button");
    buttInput.className = "col-md-6";
    // console.log( heder.querySelector("div").id);
    let id = valueOfId;
    input.className = "refresh-input" + id;
    input.name = id;
    input.setAttribute("size", "5")
    // console.log(input);
    buttInput.className = "refresh-input-button";
    buttInput.name = id;
    buttInput.textContent = "interval";
    buttInput.name = id;
    buttInput.setAttribute("onclick", "reloadData(" + id + ")");
    let scripClick = document.createElement("script");
    scripClick.innerHTML =
        "setInterval(function ()\n" +
        "            {\n" +

        "                if (document.getElementById('customSwitch" + id + "').checked)\n" +
        "                {\n" +
        "               reloadData(" + id + ");\n" +
        "                }\n" +
        "            }, time = 3000);"
    let switcher = document.createElement("div");
    switcher.className = "custom-control custom-switch";
    switcher.innerHTML = " <input type=\"checkbox\" class=\"custom-control-input\" id=\"customSwitch" + id + "\">\n" +
        "  <label class=\"custom-control-label\" for=\"customSwitch" + id + "\">Auto Refresh in 3 sec</label>\n";

    objectInput.appendChild(switcher);
    objectInput.appendChild(scripClick);
    div1.appendChild(input);
    div2.appendChild(buttInput);
    objectInput.appendChild(input);
    objectInput.appendChild(buttInput);
    body.appendChild(objectInput);


    doc.appendChild(heder);
    doc.appendChild(body);
    render.appendChild(doc);


    document.querySelector("#output").appendChild(render);
}

