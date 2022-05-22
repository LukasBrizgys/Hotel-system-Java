const form = document.getElementsByTagName("form")[0];


const services = document.getElementById('servicesCheckbox');
const meals = document.getElementById('mealsCheckbox');


form.addEventListener("click", (e) =>{
    const target = e.target;
    const priceElement = document.querySelector(".actual-price");
    let price = parseFloat(priceElement.innerText);
    if(String(target).toLowerCase().includes("input")) {
        const clickedCheckboxValue = parseFloat(target.value);
        priceElement.innerText = target.checked ? `${(price + clickedCheckboxValue).toFixed(2)}€`
            : `${(price - clickedCheckboxValue).toFixed(2)}€`;
    }
})