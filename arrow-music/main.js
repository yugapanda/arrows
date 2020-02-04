const max = require("max-api");
const r = require("request-promise");

async function get(){

    const [
        tempo,
        bass,
        rhythm1,
        melody1,
        rhythm2,
        melody2,
        rhythm3,
        melody3,
        rhythm4,
        melody4,
        rhythm5,
        melody5,
        rhythm6,
        melody6,
        rhythm7,
        melody7,
        rhythm8,
        melody8,
        rhythm9,
        melody9,
        rhythm10,
        melody10] = await r("http://192.168.24.12:8081/params");

    max.outlet(JSON.stringify(s));
}

get();
