const stars = document.querySelectorAll('.half-star-left, .half-star-right');
const markInput = document.querySelector('input[name="givenMark"]');
const authorInput = document.querySelector('input[name="uuidAuthor"]');
const ratingForm = document.getElementById('rating-form');

stars.forEach((star, index) => {
    star.addEventListener('mouseenter', () => {
        const rating = star.getAttribute('data-rating');

        for (let i = 0; i <= index; i++) {
            if (stars[i].className === "half-star-left") {
                stars[i].classList.add('half-star-left-hovered');
            } else {
                stars[i].classList.add('half-star-right-hovered');
            }
        }

        markInput.value = rating;
    });

    star.addEventListener('mouseleave', () => {
        markInput.value = 0;

        stars.forEach((star, i) => {
            star.classList.remove('half-star-left-hovered');
            star.classList.remove('half-star-right-hovered');
        });
    });

    star.addEventListener('click', () => {
        if (authorInput.value === "none") {
            alert("Vous n'êtes pas connecté. Veuillez vous connecter ou vous inscrire afin de bénéficier de cette fonctionnalité.");
            window.location.href = 'signup';
        } else {
            ratingForm.submit();
        }
    });
});


