// первым блоком script для модального окна и формы отправки

// для строки поиска

// для отрисовки карточек и их анимации

const modal = document.querySelector('#modal');
const close = document.querySelector('.modal__card-close');
const demo = document.querySelector('.demo');
const cardList = document.querySelector('.cards');


close.onclick = function () {
  modal.style.display = 'none';
};

window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = 'none';
  }
};

function autoComplete () {
  fetch ("http://localhost:8080/cards")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    const namesList = data.map(item => item.name);
    let card = "";
    for (let i = 0; i <= namesList.length; i++) {
      card += `<div class="card">
            <p class="card-titly">${namesList[i]}</p>
            <p class="card-text none">${namesList[i]}</p>
            </div>`
    }
    cardList.innerHTML = card;
    // const cardsArr = document.querySelectorAll('.card');
    // console.log(cardsArr)
    
    cardList.onclick = function (event) {
      let div = event.target.closest('div'); 

      if (!div) return; 
    
      if (!cardList.contains(div)) return; 
      
      modal.style.display = 'block';
      // console.log(div)


    }
    
  })
  .catch ((e) => {
    console.error(e);
    demo.style.display = 'flex';
  })


};

autoComplete();