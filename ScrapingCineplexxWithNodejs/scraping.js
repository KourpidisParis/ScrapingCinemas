const puppeteer = require("puppeteer");
const fs = require("fs");

(async () => {
  let movieUrl = "https://www.cineplexx.gr/tainies/";
  let browser = await puppeteer.launch();
  let page = await browser.newPage();

  await page.goto(movieUrl, { waitUntil: "networkidle2" });

  let data = await page.evaluate(() => {
    let names = [];
    let links = [];
    let images = [];
    let times = [];
    let rooms = [];
    let modes = [];

    let items = document.querySelectorAll(
      'div[class="overview-element separator"]'
    );
    for (let item of items) {
      name = item.querySelector("h2").textContent;
      link = item.querySelector('div[class="info clearfix"] > a').href;

      // image = item.querySelector('img[class="film-teaser-image lazy"]').src;
      image = item.querySelector('img[class="film-teaser-image lazy"]').dataset
        .original;
      // info for times
      details = item.querySelectorAll('div[class="row-fluid start-times"]');
      for (let info of details) {
        time = info.querySelector('p[class="time-desc"]').innerText;
        room = info.querySelector('p[class="room-desc"]').innerText;
        mode = info.querySelector('p[class="mode-desc"]').innerText;

        times.push(time);
        rooms.push(room);
        modes.push(mode);
      }

      names.push(name);
      links.push(link);
      images.push(image);
    }

    return {
      names,
      links,
      images,
      times,
      rooms,
      modes,
    };
  });

  console.log(data);

  await browser.close();
})();
