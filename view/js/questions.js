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
        const newDiv = document.createElement(`p`)
        newDiv.setAttribute("id", item);
        newDiv.innerHTML = `<input type="radio" name="spec" value=${item}> ${item}<Br>`;
        my_div = document.getElementById("org_div1");
        document.body.insertBefore(newDiv, my_div);
    });
}

getSpecs();

async function createQuestion() {
    let spec = document.querySelector('input[name="spec"]:checked').value;
    let question = document.querySelector('input[name="question"]').value;
    let answer = document.querySelector('input[name="answer"]').value;
    let body = JSON.stringify({question, answer})
    const result = await fetch(`http://localhost:8080/api/create?spec=${spec}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
        },
        body: body,
    });
    if (result.ok) {
        alert("Вопрос успешно добавлен")
        document.getElementById("question").value = ""
        document.getElementById("answer").value = ""
    }

}

