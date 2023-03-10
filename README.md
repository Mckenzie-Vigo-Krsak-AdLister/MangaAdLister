# Manga Adlister
Manga adlister is allows users to post listings of Mangas for sale.
Users can browse different categories of Manga and select as many as
they'd like and store them in their server side cart for purchase.

## Contribuitors
- John Krsak        - Developer
- Alvin Mckenzie    - Developer
- Aldanis Vigo      - Developer

## Database Schema
![Database Schema](docs/img/db_schema.png)

## Registration & Account Recovery Flow
![Registration Flow](docs/img/register_flow.jpg)
<a href="docs/img/register_flow.pdf">Download PDF</a>

## Login & Account Recovery Flow
![Login Flow](docs/img/login_flow.jpg)
<a href="docs/img/login_flow.pdf">Download PDF</a>

## Post Listing Flow

When the user gets to the Manga Listings page, there will be user input options available to them on the page.
One of these options will be to post their own listings. Since the listings page will be available to the public
the first thing we do is check if the user has already been authenticated. If so, we redirect them to the page where
they can fill out the details of their listing. If not, we redirect them to the login page where they can either
fill in their auth details 

## Filter Listings Flow

## Send Messages Flow

## Read Messages Flow

## Add Product To Cart Flow

## Purchase Flow

## Mocked SMTP Email Server

In order to reduce costs during development smtp4dev was used. 
the configuration at Config/Config.java is set to use smtp4dev running from a
docker container.

To install docker go to https://docs.docker.com/desktop/install/mac-install/

Once docker is started, you can launch smtp4dev using the following command.

`docker run -p 3000:80 -p 2525:25 -d --name smtpdev rnwood/smtp4dev`

To view the outgoing mocked emails visit [Here]('http://localhost:3000') after launching the docker container using the command above.


