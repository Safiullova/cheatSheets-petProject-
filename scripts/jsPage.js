
// START MODAL WINDOW
const modalWindowCard = document.getElementById('myModal');
const btnAddCard = document.getElementById("myBtn");
const btnClose = document.getElementsByClassName("modal__close")[0];

const bntPOSTCard = document.getElementById('submitCard');

btnAddCard.onclick = function() {
    modalWindowCard.style.display = "block";
}

btnClose.onclick = function() {
    modalWindowCard.style.display = "none";
}

// Функция для добавления карточки

bntPOSTCard.onclick = function (e) {
  e.preventDefault();

  const nameCard = document.querySelector('#nameCard').value;
  const textCard = document.getElementById('textCard').value;

  const newCard = {
    name: `${nameCard}`,
    description: `${textCard}`
 }
  console.log(newCard);

  fetch("http://localhost:8080/cards?themeId=1",
      {
          method: 'POST',
          headers: { "Content-Type": "application/json;charset=utf-8" },
          body: JSON.stringify(newCard),
      })
      .then(response => response.json())
      .then(card => {
          console.log(card);
      })
      .catch(error => console.log(error));

      getCards() //Обновление всех карточек
}

// END MODAL WINDOW

// для строки поиска


// для отрисовки карточек и их анимации

const cardList = document.querySelector('.cards');
const demo = document.querySelector('.demo');
const loader = document.querySelector('.loader');

window.setTimeout(() => {
  getCards();
}, 3000); // Задержка 3 секунды, крутится лоадер

function getCards () { // функция получения всех карточек по теме
  fetch ("http://localhost:8080/getcardsbytheme?themeId=1")

  .then((response) => {
    return response.json();
  })

  .then((data) => {

    let card = "";

    for (let i = 0; i < data.length; i++) {

      const item = new Object(data[i]);
      card += ` <label id=${item['id']}>
        <input type="checkbox"  />
        <div class="card">
          <div class="front">
            <p class="card-titly">${item['name']}</p>
          </div>
          <div class="back">
            <p class="card-titly">${item['name']}</p>
            <p class="card-text">${item['description']}</p>
          </div>
          <div class="delCard" name='del'></div>
        </div>
      </label>`
    };

    cardList.innerHTML = card;

  })

  .catch ((e) => {
    console.error(e);
    demo.classList.remove("none");
  })

  .finally(() => {
    loader.classList.add("none");
  });
};

cardList.onclick = function (e) { //отлов клика id для удаления карточки
  if (e.target.className === 'delCard') {

  id = `${e.target.parentElement.parentElement.id}`;
  if (confirm('Удалить карточку?') == true) {
    deleteCard (id); //функция удаления карточки
  } else {
    return
  }

  setTimeout(() => {
    getCards();
  }, 500); //Обновление всех карточек

  }
  return
};

function deleteCard (id) { //функция удаления карточки

  fetch("http://localhost:8080/cards/" + id,
  {
      method: 'DELETE',
  })

  .catch(error => console.log(error));
};


const btnUp = {
  el: document.querySelector('.btn-up'),
  scrolling: false,
  show() {
    if (this.el.classList.contains('btn-up_hide') && !this.el.classList.contains('btn-up_hiding')) {
      this.el.classList.remove('btn-up_hide');
      this.el.classList.add('btn-up_hiding');
      window.setTimeout(() => {
        this.el.classList.remove('btn-up_hiding');
      }, 300);
    }
  },
  hide() {
    if (!this.el.classList.contains('btn-up_hide') && !this.el.classList.contains('btn-up_hiding')) {
      this.el.classList.add('btn-up_hiding');
      window.setTimeout(() => {
        this.el.classList.add('btn-up_hide');
        this.el.classList.remove('btn-up_hiding');
      }, 300);
    }
  },
  addEventListener() {
    // при прокрутке окна (window)
    window.addEventListener('scroll', () => {
      const scrollY = window.scrollY || document.documentElement.scrollTop;
      if (this.scrolling && scrollY > 0) {
        return;
      }
      this.scrolling = false;
      // если пользователь прокрутил страницу более чем на 200px
      if (scrollY > 400) {
        // сделаем кнопку .btn-up видимой
        this.show();
      } else {
        // иначе скроем кнопку .btn-up
        this.hide();
      }
    });
    // при нажатии на кнопку .btn-up
    document.querySelector('.btn-up').onclick = () => {
      this.scrolling = true;
      this.hide();
      // переместиться в верхнюю часть страницы
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: 'smooth'
      });
    }
  }
}

btnUp.addEventListener();