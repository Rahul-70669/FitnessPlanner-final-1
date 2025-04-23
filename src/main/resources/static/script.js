// script.js
document.addEventListener('DOMContentLoaded', () => {
    // ------ NAVIGATION ------
    const userSection = document.getElementById('userSection');
    const planSection = document.getElementById('planSection');
    document.getElementById('showUsers').onclick = () => {
        userSection.style.display = 'block';
        planSection.style.display = 'none';
    };
    document.getElementById('showPlans').onclick = () => {
        userSection.style.display = 'none';
        planSection.style.display = 'block';
    };

    // ------ USER LOGIC ------
    const userAPI = 'http://localhost:8080/api/users';
    const userForm = document.getElementById('userForm');
    const userList = document.getElementById('userList');
    const userSearchResult = document.getElementById('userSearchResult');

    userForm.addEventListener('submit', async e => {
        e.preventDefault();
        const u = {
            name: name.value,
            email: email.value,
            password: password.value,
            age: +age.value,
            gender: gender.value,
            height: +height.value,
            weight: +weight.value,
            healthissue: healthissue.value,
            transformationtype: transformationtype.value
        };
        const res = await fetch(`${userAPI}/add`, {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(u)
        });
        if (res.ok) {
            userForm.reset();
            loadUsers();
        } else {
            alert('Failed to add user');
        }
    });

    async function loadUsers() {
        const res = await fetch(userAPI);
        const arr = await res.json();
        userList.innerHTML = arr.map(u => userCard(u)).join('');
    }

    function userCard(u) {
        return `
      <div class="card">
        <strong>${u.name}</strong> (ID: ${u.id})<br/>
        Age: ${u.age} | Gender: ${u.gender}<br/>
        Height: ${u.height} cm | Weight: ${u.weight} kg<br/>
        Transformation: ${u.transformationtype}<br/>
        <div class="inline-form">
          <input type="number" id="h-${u.id}" placeholder="Hgt" min="100"/>
          <button onclick="updateHeight(${u.id})">H+</button>
        </div>
        <div class="inline-form">
          <input type="number" id="w-${u.id}" placeholder="Wgt" min="20"/>
          <button onclick="updateWeight(${u.id})">W+</button>
        </div>
        <div class="inline-form">
          <select id="t-${u.id}">
            <option value="">Type</option>
            <option>Gaining</option>
            <option>Leaning</option>
          </select>
          <button onclick="updateTransformation(${u.id})">T+</button>
        </div>
        <button onclick="deleteUser(${u.id})">Delete</button>
      </div>`;
    }

    window.deleteUser = async id => {
        if (!confirm('Delete this user?')) return;
        await fetch(`${userAPI}/${id}`, {method:'DELETE'});
        loadUsers();
    };
    window.updateHeight = async id => {
        const h = document.getElementById(`h-${id}`).value;
        if (!h) return alert('Enter a height');
        await fetch(`${userAPI}/height/${id}?height=${h}`, {method:'PUT'});
        loadUsers();
    };
    window.updateWeight = async id => {
        const w = document.getElementById(`w-${id}`).value;
        if (!w) return alert('Enter a weight');
        await fetch(`${userAPI}/weight/${id}?weight=${w}`, {method:'PUT'});
        loadUsers();
    };
    window.updateTransformation = async id => {
        const t = document.getElementById(`t-${id}`).value;
        if (!t) return alert('Select a type');
        await fetch(`${userAPI}/byName/${id}?transformationtype=${t}`, {method:'PUT'});
        loadUsers();
    };

    document.getElementById('btnSearchUserById').onclick = async () => {
        const id = document.getElementById('searchUserId').value;
        if (!id) return;
        const res = await fetch(`${userAPI}/${id}`);
        const u = await res.json();
        userSearchResult.innerHTML = userCard(u);
    };
    document.getElementById('btnSearchUserByName').onclick = async () => {
        const nameQ = encodeURIComponent(document.getElementById('searchUserName').value);
        if (!nameQ) return;
        const res = await fetch(`${userAPI}/byName?name=${nameQ}`);
        const u = await res.json();
        userSearchResult.innerHTML = userCard(u);
    };

    // init users
    loadUsers();


    // ------ PLAN LOGIC ------
    const planAPI = 'http://localhost:8080/api/workoutplans';
    const planForm = document.getElementById('planForm');
    const planList = document.getElementById('planList');
    const planSearchResult = document.getElementById('planSearchResult');

    planForm.addEventListener('submit', async e => {
        e.preventDefault();
        const p = {
            name: planName.value,
            description: planDescription.value,
            difficultyLevel: difficultyLevel.value,
            userId: +planUserId.value,
            transformationtype: planTransformationType.value
        };
        const res = await fetch(`${planAPI}/add`, {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(p)
        });
        if (res.ok) {
            planForm.reset();
            loadPlans();
        } else {
            alert('Failed to add plan');
        }
    });

    async function loadPlans() {
        const res = await fetch(planAPI);
        const arr = await res.json();
        planList.innerHTML = arr.map(p => planCard(p)).join('');
    }

    function planCard(p) {
        return `
      <div class="card">
        <strong>${p.name}</strong> (ID: ${p.id})<br/>
        Level: ${p.difficultyLevel} | User ID: ${p.userId}<br/>
        Type: ${p.transformationtype}<br/>
        ${p.description}<br/>
        <div class="inline-form">
          <input type="text" id="d-${p.id}" placeholder="New Desc"/>
          <button onclick="updateDesc(${p.id}, ${p.userId})">UpdDesc</button>
        </div>
        <div class="inline-form">
          <input type="number" id="u-${p.id}" placeholder="New UID"/>
          <button onclick="updatePlanUser(${p.id})">UpdUser</button>
        </div>
        <button onclick="deletePlan(${p.id})">Delete</button>
      </div>`;
    }

    window.deletePlan = async id => {
        if (!confirm('Delete this plan?')) return;
        await fetch(`${planAPI}/${id}`, {method:'DELETE'});
        loadPlans();
    };
    window.updateDesc = async (id, userId) => {
        const desc = document.getElementById(`d-${id}`).value;
        if (!desc) return alert('Enter a description');
        await fetch(`${planAPI}/user/${userId}`, {
            method:'PUT',
            headers:{'Content-Type':'application/json'},
            body: JSON.stringify({ id, description: desc })
        });
        loadPlans();
    };
    window.updatePlanUser = async id => {
        const newUid = document.getElementById(`u-${id}`).value;
        if (!newUid) return alert('Enter a user ID');
        await fetch(`${planAPI}/userId/${id}?userId=${newUid}`, {method:'PUT'});
        loadPlans();
    };

    document.getElementById('btnSearchPlanById').onclick = async () => {
        const id = document.getElementById('searchPlanId').value;
        if (!id) return;
        const res = await fetch(`${planAPI}/${id}`);
        const p = await res.json();
        planSearchResult.innerHTML = planCard(p);
    };
    document.getElementById('btnSearchPlanByUserId').onclick = async () => {
        const uid = document.getElementById('searchPlansByUserId').value;
        if (!uid) return;
        const res = await fetch(`${planAPI}/user/${uid}`);
        const arr = await res.json();
        planSearchResult.innerHTML = arr.length
            ? arr.map(p => planCard(p)).join('')
            : 'No plans found';
    };

    // init plans
    loadPlans();
});
