(function (global) {
    //modals

    const button_modals = document.querySelectorAll('[data-modal]');

    button_modals.forEach((btn) => {
        btn.onclick = (event) => {
            let modalID = btn.getAttribute('data-modal');
            let modal = document.querySelector('#' + modalID);
            modal.classList.toggle('modal--show');
        };
    });


    function myFunction() {
        // Get the snackbar DIV
        var x = document.getElementById("snackbar");

        // Add the "show" class to DIV
        x.className = "show";

        // After 3 seconds, remove the show class from DIV
        setTimeout(function () {
            x.className = x.className.replace("show", "");
        }, 3000);
    }

    const snacks = document.querySelectorAll(".snack");

    for (let i = 0; i < snacks.length; i++) {
        const singleSnack = snacks[i];
        singleSnack.classList.add("show");
        setTimeout(function () {
            singleSnack.className = singleSnack.className.replace("show", "");
        }, 3000);
    }


    //menu

    let elms = document.querySelectorAll('.inactive');
    let menuBtn = document.querySelector('#menu_btn');

    function showMenu() {
        if (menuBtn.classList.contains("closed")) {
            menuBtn.classList = "mobile-burger open";
        } else {
            menuBtn.classList = "mobile-burger closed";
        }
        elms.forEach(function (elm) {
            elm.classList.toggle("inactive");
        })
    }

    menuBtn.addEventListener("click", showMenu);

    const acc = document.querySelectorAll("[data-accordion]");

    function slide(panel, num) {
        var num = num || panel.scrollHeight + "px";
        if (panel.style.maxHeight) {
            panel.style.maxHeight = null;
        } else {
            panel.style.maxHeight = num;
        }
    }


    for (let i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            this.classList.toggle("accordion-btn--active");
            slide(this.nextElementSibling)
        });
    }

})(window);
