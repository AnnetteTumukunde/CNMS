const menuIcon = document.querySelector('.menuIcon');
const nav = document.querySelector('#nav');
const header = document.querySelectorAll('header');
let index = 0;
const [left, right] = [document.querySelector('#left'), document.querySelector('#right')];

header[0].classList.add('active');

menuIcon.addEventListener('click', toggleMenu);
left.addEventListener('click', prevSlide)
right.addEventListener('click', nextSlide)

function toggleMenu() {
    nav.classList.toggle('active')
}

function nextSlide() {
    header[index].classList.remove('active');
    index = (index + 1 + header.length) % header.length
    console.log(index);
    header[index].classList.add('active');

}

function prevSlide() {
    header[index].classList.remove('active');
    index = (index - 1 + header.length) % header.length
    console.log(index);
    header[index].classList.add('active');

}