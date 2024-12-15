function mystery(){
    const nbAlea = Math.floor(Math.random() * 3) + 1;

    switch (nbAlea) {
        case 1:
            window.open("https://www.linkedin.com/in/helene-delran/", "_blank");
            break;
        case 2:
            window.open("https://www.linkedin.com/in/rollet-quentin/", "_blank");
            break;
        case 3:
            window.open("https://www.linkedin.com/in/matt-coste/", "_blank");
            break;

        default:
            console.log("Nombre inattendu");
            break;
    }

}