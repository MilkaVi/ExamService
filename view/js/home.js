async function getSpecs() {
    const result = await fetch(
        `http://localhost:8080/api/specs`,
        {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        }
    );
    let specs = await result.json();

    var my_div = newDiv = null;

    specs.forEach(function (item) {
        const newDiv = document.createElement("div")
        newDiv.setAttribute("id", item);
        newDiv.innerHTML = `<h1>${item}</h1><input type="text" size="10">`;
        my_div = document.getElementById("org_div1");
        document.body.insertBefore(newDiv, my_div);
    });
}

getSpecs();

async function getExam() {
    const resultSpec = await fetch(
        `http://localhost:8080/api/specs`,
        {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        }
    );
    let specs = await resultSpec.json();
    let body = {}


    specs.forEach(function (item) {

        let temp = document.querySelector(`#${item} input`)
        body[item] = +temp.value
    });

    let result = await fetch(
        `http://localhost:8080/api/exam`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(body),
        }
    );
    let questions = await result.json();
    questions = questions.sections

    const table = document.querySelector('tbody');

    questions.forEach(function (item) {
        const newDiv = document.createElement("div")
        newDiv.innerHTML = `<h2>${item.name}:`;
        my_div = document.getElementById("org_div2");
        document.body.insertBefore(newDiv, my_div);

        let q = item.questions
        q.forEach(function (i) {
            console.log(i)
            const newElement = document.createElement('tr');
            newElement.innerHTML = `
			<td>${i.question}</td>
			<td>|${i.answer}</td>`;
            document.body.insertBefore(newElement, my_div);
        })
    });
}
