document.addEventListener('DOMContentLoaded', function () {

// Fonction pour ajouter une div "response"
    function addResponse() {
        const responsesDiv = this.parentElement;
        let responseCount = responsesDiv.querySelectorAll('.response').length;
        const newResponseDiv = document.createElement('div');
        newResponseDiv.classList.add('response');
        newResponseDiv.innerHTML = `
            <label for="response-${responseCount + 1}-${responseCount + 1}"> Answer ${responseCount + 1}</label>
            <div>
                <input type="checkbox" id="isGoodAnswer-${responseCount + 1}-${responseCount + 1}">
                <input type="text" name="response" id="response-${responseCount + 1}-${responseCount + 1}" class="text-input mid-input">
                <button class="deleteResponse button boxed-button" type="button"> X </button>
            </div>
        `;
        newResponseDiv.addEventListener("input", updateQuiz);
        responsesDiv.insertBefore(newResponseDiv, this);
        updateDeleteResponseButtons();

        // Update delete answer button
        responseCount = responsesDiv.querySelectorAll('.response').length;
        if (responseCount > 2){
            responsesDiv.querySelectorAll('.response').forEach((response, index) => {
                const button = response.querySelector('.deleteResponse');
                button.disabled = false;
            });
        }
    }

    // Fonction pour ajouter une div "question"
    function addQuestion() {
        const questionsDiv = this.parentElement;
        let questionCount = questionsDiv.children.length - 1; // Exclut le bouton "addQuestion"
        const newQuestionDiv = document.createElement('div');
        newQuestionDiv.classList.add('question');
        newQuestionDiv.innerHTML = `
            
            <label for="question-${questionCount + 1}"> Question ${questionCount + 1} </label>
            <div>
                <input type="text" name="question" id="question-${questionCount + 1}" class="text-input big-input">
                <button class="deleteQuestion button boxed-button" type="button"> X </button>
            </div>
            <div class="responses">
                <div class="response">
                    <label for="response-${questionCount + 1}-1"> Answer 1</label>
                    <div>
                        <input type="checkbox" id="isGoodAnswer-${questionCount + 1}-1">
                        <input type="text" name="response" id="response-${questionCount + 1}-1" class="text-input mid-input">
                        <button class="deleteResponse button boxed-button" type="button" disabled> X </button>
                    </div>
                </div>
                <div class="response">
                    <label for="response-${questionCount + 1}-2"> Answer 2</label>
                    <div>
                        <input type="checkbox" id="isGoodAnswer-${questionCount + 1}-2">
                        <input type="text" name="response" id="response-${questionCount + 1}-2" class="text-input mid-input">
                        <button class="deleteResponse button boxed-button" type="button" disabled> X </button>                       
                    </div>
                </div>
                <button class="addAnswer button boxed-button" type="button"> + Add answers </button>
            </div>
        `;
        newQuestionDiv.addEventListener("input", updateQuiz);
        questionsDiv.insertBefore(newQuestionDiv, this);
        updateDeleteQuestionButtons();
        updateDeleteResponseButtons();
        updateAddAnswerButtons();

        // Update delete question button
        questionCount = questionsDiv.querySelectorAll('.question').length;
        if (questionCount > 1){
            questionsDiv.querySelectorAll('.question').forEach((question, index) => {
                const button = question.querySelector('.deleteQuestion');
                button.disabled = false;
            });
        }
    }

    // Fonction pour supprimer la réponse associée
    function deleteResponse() {
        const responseDiv = this.parentElement.parentElement;
        const responsesDiv = responseDiv.parentElement.parentElement;
        let responseCount = responsesDiv.querySelectorAll('.response').length;

        // Supprimer la réponse
        if (responseCount > 2){
            responseDiv.remove();
            updateDeleteResponseButtons();
        }

        // Mettre à jour la numérotation des réponses
        responsesDiv.querySelectorAll('.response').forEach((response, index) => {
            const label = response.querySelector('label');
            const input = response.querySelector('input[type="text"]');
            const button = response.querySelector('.deleteResponse');

            label.setAttribute('for', `response-${index + 1}-${index + 1}`);
            label.textContent = `Answer ${index + 1}`;

            input.setAttribute('id', `response-${index + 1}-${index + 1}`);
            button.dataset.responseIndex = index + 1;
        });

        // Update delete answer button
        responseCount = responsesDiv.querySelectorAll('.response').length;
        if (responseCount === 2){
            responsesDiv.querySelectorAll('.response').forEach((response, index) => {
                const button = response.querySelector('.deleteResponse');
                button.disabled = true;
            });
        }
    }

    // Fonction pour supprimer la question associée
    function deleteQuestion() {

        const questionDiv = this.parentElement.parentElement;
        const questionsDiv = questionDiv.parentElement.parentElement;
        let questionCount = questionsDiv.querySelectorAll('.question').length;

        // Vérifier s'il reste plus d'une question
        if (questionCount > 1) {
            // Supprimer la question
            questionDiv.remove();
            updateDeleteQuestionButtons();
        }

        // Update delete question button
        questionCount = questionsDiv.querySelectorAll('.question').length;
        if (questionCount === 1){
            questionsDiv.querySelectorAll('.question').forEach((question, index) => {
                const button = question.querySelector('.deleteQuestion');
                button.disabled = true;
            });
        }

        // Mettre à jour la numérotation des questions
        questionsDiv.querySelectorAll('.question').forEach((question, index) => {
            const label = question.querySelector('label');
            const input = question.querySelector('input[type="text"]');
            const button = question.querySelector('.deleteQuestion');

            label.setAttribute('for', `question-${index + 1}-${index + 1}`);
            label.textContent = `Question ${index + 1}`;

            input.setAttribute('id', `question-${index + 1}-${index + 1}`);
            button.dataset.questionIndex = index + 1;
        });
    }

    // Fonction pour mettre à jour les boutons "deleteResponse"
    function updateDeleteResponseButtons() {
        const deleteResponseButtons = document.querySelectorAll('.deleteResponse');
        deleteResponseButtons.forEach(button => {
            button.addEventListener('click', deleteResponse);
        });
    }

    // Fonction pour mettre à jour les boutons "deleteQuestion"
    function updateDeleteQuestionButtons() {
        const deleteQuestionButtons = document.querySelectorAll('.deleteQuestion');
        deleteQuestionButtons.forEach(button => {
            button.addEventListener('click', deleteQuestion);
        });
    }

    // Fonction pour mettre à jour les boutons "addAnswer"
    function updateAddAnswerButtons() {
        const addAnswerButtons = document.querySelectorAll('.addAnswer');
        addAnswerButtons.forEach(button => {
            button.addEventListener('click', addResponse);
        });
    }

    // Ajout des écouteurs d'événements pour les boutons existants
    updateDeleteResponseButtons();
    updateDeleteQuestionButtons();
    updateAddAnswerButtons();

    // Ajout d'un écouteur d'événement pour le bouton "addQuestion"
    const addQuestionButton = document.querySelector('.addQuestion');
    addQuestionButton.addEventListener('click', addQuestion);
});

// On submit. Create a JSON object with all the questions and answers and send it with a fetch
const form = document.getElementById("createForm");

// add event listener on each input fields
const inputs = form.querySelectorAll("input");
inputs.forEach(function (input, index) {
    input.addEventListener("input", updateQuiz)
})

function updateQuiz() {
    const quizTitle = document.querySelector('input[name="quizTitle"]').value;
    const questions = [];

    // take all the questions div element
    const questionsElements = document.querySelectorAll(".question");
    questionsElements.forEach(function (questionElement, index) {
        const questionTitle = questionElement.querySelector('input[name="question"]').value;
        const answers = [];

        // take all the answers div element
        const answerElements = questionElement.querySelectorAll(".response");
        answerElements.forEach(function(answerElement, subIndex) {
            const answerText = answerElement.querySelector('input[name="response"]').value;
            const isGoodAnswer = answerElement.querySelector('input[type="checkbox"]').checked;

            answers.push({
                title: answerText,
                isGoodAnswer: isGoodAnswer
            });
        });

        questions.push({
            title: questionTitle,
            answers: answers
        });
    });

    const quizData = {
        title: quizTitle,
        questions: questions
    };

    // convert quizData to String
    const quizJSON = JSON.stringify(quizData);

    const quizInputElement = document.getElementById("jsonQuiz");
    quizInputElement.value = quizJSON;
}