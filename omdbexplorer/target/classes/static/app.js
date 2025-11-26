document.getElementById("search-btn").addEventListener("click", searchMovie);

async function searchMovie() {
    const query = document.getElementById("search-input").value.trim();
    const resultsDiv = document.getElementById("results");

    if (!query) {
        alert("Please enter a movie name!");
        return;
    }

    try {
        const response = await fetch(`http://localhost:9090/api/search?title=${query}`);
        const data = await response.json();
        const movies = data.Search || [];

        if (movies.length === 0) {
            resultsDiv.innerHTML = `<p style="text-align:center; color: var(--silver)">No results found!</p>`;
            return;
        }

        resultsDiv.innerHTML = movies.map(movie => `
            <div class="movie-card" onclick="showDetails('${movie.imdbID}')">
                <img src="${movie.Poster}" alt="${movie.Title}">
                <div class="movie-title">${movie.Title}</div>
                <div class="movie-year">${movie.Year} (${movie.Type})</div>
            </div>
        `).join("");

    } catch (error) {
        console.error(error);
        resultsDiv.innerHTML = `<p style="text-align:center; color: red;">Backend error! Check server.</p>`;
    }
}

// Show movie details
async function showDetails(imdbID) {
    try {
        const response = await fetch(`http://localhost:9090/api/details?imdbID=${imdbID}`);
        const movie = await response.json();

        const resultsDiv = document.getElementById("results");
        resultsDiv.innerHTML = `
            <div class="movie-card">
                <img src="${movie.Poster}" alt="${movie.Title}">
                <div class="movie-title">${movie.Title}</div>
                <div class="movie-year">${movie.Year} (${movie.Type})</div>
                <p><strong>Director:</strong> ${movie.Director}</p>
                <p><strong>Actors:</strong> ${movie.Actors}</p>
                <p><strong>Plot:</strong> ${movie.Plot}</p>
                <p><strong>IMDB Rating:</strong> ${movie.imdbRating}</p>
                <button onclick="searchMovie()">Back to search</button>
            </div>
        `;
    } catch (error) {
        console.error(error);
        alert("Error fetching movie details!");
    }
}
