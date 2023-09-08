<%-- 
    Document   : newjsp
    Created on : 5 mai 2023, 14:01:13
    Author     : stag
--%>

<c:choose>
    <c:when test="${sessionScope.user.id == 1}">
        <link rel="stylesheet" href="<c:url value="/rsc/css/Admin.css"/>"/>
    </c:when>
</c:choose>
<style>
    form {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin: 0 auto;
        width: 40%;
        padding: 30px;
        border-radius: 10px;
        background: #2B3A42;
        box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.5);
    }

    .error {
        color: red;
        font-size: 1.8rem;
        font-style: italic;
    }

    h1 {
        color: #F7DC6F;
        text-align: center;
        font-size: 2.5rem;
    }

    label {
        color: #F7DC6F;
        font-size: 1.5rem;
        font-weight: bold;
        margin-bottom: 10px;
    }

    input[type=text],input[type=email],  input[type=password] {
        padding: 15px;
        border: none;
        border-radius: 10px;
        margin-bottom: 20px;
        width: 100%;
        background-color: #F7DC6F;
        color: #2B3A42;
        font-size: 1.2rem;
    }

    input[type=submit], input[type=reset] {
        background-color: #F7DC6F;
        color: #2B3A42;
        padding: 15px 30px;
        border: none;
        border-radius: 10px;
        font-size: 1.2rem;
        cursor: pointer;
    }

    input[type=submit]:hover , input[type=reset]:hover{
        background-color: #34495E;
        color: #F7DC6F;
    }

    .error-message {
        color: #ff0000;
        font-size: 18px;
        font-family: 'Bebas Neue', sans-serif;
        text-transform: uppercase;
        background-color: #0d0d0d;
        border: 2px solid #ff0000;
        padding: 10px;
        margin-bottom: 10px;
    }

    .error-message:before {
        content: "!";
        color: #ff0000;
        font-weight: bold;
        font-size: 32px;
        display: inline-block;
        margin-right: 10px;
        transform: translateY(-10%);
    }


    .containerArticle {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        gap: 20px;
    }

    .box {
        background-color: #fff;
        box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
        padding: 20px;
    }

    @media (max-width: 768px) {
        .container {
            grid-template-columns: repeat(2, 1fr);
        }
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin: 0 auto;
        width: 40%;
        padding: 30px;
        border-radius: 10px;
        background: #2B3A42;
        box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.5);
    }

    h1 {
        color: #F7DC6F;
        text-align: center;
        font-size: 2.5rem;
    }

    label {
        color: #F7DC6F;
        font-size: 1.5rem;
        font-weight: bold;
        margin-bottom: 10px;
    }

    input[type=text],input[type=email], input[type=password] {
        padding: 15px;
        border: none;
        border-radius: 10px;
        margin-bottom: 20px;
        width: 100%;
        background-color: #F7DC6F;
        color: #2B3A42;
        font-size: 1.2rem;
    }

    input[type=submit] , input[type=reset]{
        background-color: #F7DC6F;
        color: #2B3A42;
        padding: 15px 30px;
        border: none;
        border-radius: 10px;
        font-size: 1.2rem;
        cursor: pointer;
    }

    input[type=submit]:hover , input[type=reset]:hover{
        background-color: #34495E;
        color: #F7DC6F;
    }

    .error-message {
        color: red;
        font-weight: bold;
    }
    textarea {
        padding: 15px;
        border: none;
        border-radius: 10px;
        margin-bottom: 20px;
        width: 100%;
        height: 150px; /* Hauteur du textarea */
        background-color: #F7DC6F;
        color: #2B3A42;
        font-size: 1.2rem;
        resize: vertical; /* Permet à l'utilisateur de redimensionner verticalement le textarea */
    }
    .footer {
        background-color: #333;
        color: #fff;
        padding: 20px;
        text-align: center;
    }
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
        padding-bottom: 40px;
    }

    .pagination ul {
        display: flex;
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .pagination ul li {
        margin-right: 5px;
    }

    .pagination ul li.disabled a {
        color: #ccc;
        cursor: not-allowed;
        text-decoration: none;
    }

    .pagination ul li.active a {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
        text-decoration: none;
    }

    .pagination ul li a {
        display: block;
        padding: 5px 10px;
        color: #007bff;
        background-color: #fff;
        border: 1px solid #007bff;
        text-decoration: none;
    }

    .pagination ul li a:hover {
        background-color: #007bff;
        color: #fff;
        text-decoration: none;
    }

    .article-container {
        text-align: center;
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
    }

    #pageArticle > h2 {
        font-size: 2.5rem;
        font-weight: bold;
        margin-bottom: 1rem;
    }

    #pageArticle > .sub {
        font-size: 1rem;
        color: gray;
        margin-bottom: 1rem;
    }

    #pageArticle > div {
        font-size: 1.2rem;
        line-height: 1.5;
        margin-bottom: 2rem;
    }

    #pageArticle > .pagingArticle {
        display: flex;
        justify-content: space-between;
        margin-top: 2rem;
    }

    #pageArticle > a {
        font-size: 1.2rem;
        color: black;
        text-decoration: none;
        border: 1px solid black;
        padding: 0.5rem 1rem;
        border-radius: 5px;
        transition: all 0.2s ease-in-out;

    }

    #pageArticle > a:hover {
        background-color: black;
        color: white;
    }
    #pageArticle > .disabled {
        color: gray;
        border-color: gray;
        pointer-events: none;
        opacity: 0.6;
    }

    .article {
        background-color: #f7f7f7;
        border: 1px solid #ccc;
        border-radius: 5px;
        padding: 10px;
        margin-bottom: 20px;
    }

    .article h3 {
        font-size: 24px;
        margin-top: 0;
    }

    .article .sub {
        font-size: 14px;
        margin-bottom: 10px;
    }

    .article p {
        font-size: 16px;
        line-height: 1.5;
        margin-bottom: 20px;
    }

    .article .readMore {
        text-align: right;
        margin-top: 0;
    }

    .article .readMore a {
        color: #1a1aff;
        text-decoration: none;
        font-size: 18px;
    }

    .article .readMore a:hover {
        text-decoration: underline;
    }

    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 30px;
    }

    .pagination ul {
        list-style: none;
        margin: 0;
        padding: 0;
        display: flex;
    }

    .pagination li {
        margin: 0 5px;
    }

    .pagination li a {
        display: block;
        padding: 5px 10px;
        color: #fff;
        background-color: #1a1aff;
        text-decoration: none;
        border-radius: 5px;
    }

    input[type="date"] {
        appearance: none;
        -webkit-appearance: none;
        border: 1px solid #ccc;
        border-radius: 4px;
        padding: 6px 10px;
        font-size: 16px;
        line-height: 1.4;
    }

    .pagination li a:hover {
        background-color: #0000ff;
    }

    .pagination li.active a {
        background-color: #0000ff;
    }

    .pagination li.disabled a {
        background-color: #ccc;
        cursor: not-allowed;
    }
    form {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin: 0 auto;
        width: 40%;
        padding: 30px;
        border-radius: 10px;
        background: #2B3A42;
        box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.5);
    }

    .error {
        color: red;
        font-size: 1.8rem;
        font-style: italic;
    }

    h1 {
        color: #F7DC6F;
        text-align: center;
        font-size: 2.5rem;
    }

    label {
        color: #F7DC6F;
        font-size: 1.5rem;
        font-weight: bold;
        margin-bottom: 10px;
    }

    input[type=text],input[type=email],  input[type=password] {
        padding: 15px;
        border: none;
        border-radius: 10px;
        margin-bottom: 20px;
        width: 100%;
        background-color: #F7DC6F;
        color: #2B3A42;
        font-size: 1.2rem;
    }

    input[type=submit], input[type=reset] {
        background-color: #F7DC6F;
        color: #2B3A42;
        padding: 15px 30px;
        border: none;
        border-radius: 10px;
        font-size: 1.2rem;
        cursor: pointer;
    }

    input[type=submit]:hover , input[type=reset]:hover{
        background-color: #34495E;
        color: #F7DC6F;
    }

    .error-message {
        color: #ff0000;
        font-size: 18px;
        font-family: 'Bebas Neue', sans-serif;
        text-transform: uppercase;
        background-color: #0d0d0d;
        border: 2px solid #ff0000;
        padding: 10px;
        margin-bottom: 10px;
    }

    .error-message:before {
        content: "!";
        color: #ff0000;
        font-weight: bold;
        font-size: 32px;
        display: inline-block;
        margin-right: 10px;
        transform: translateY(-10%);
    }


    .containerArticle {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        gap: 20px;
    }

    .box {
        background-color: #fff;
        box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
        padding: 20px;
    }

    @media (max-width: 768px) {
        .container {
            grid-template-columns: repeat(2, 1fr);
        }
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin: 0 auto;
        width: 40%;
        padding: 30px;
        border-radius: 10px;
        background: #2B3A42;
        box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.5);
    }

    h1 {
        color: #F7DC6F;
        text-align: center;
        font-size: 2.5rem;
    }

    label {
        color: #F7DC6F;
        font-size: 1.5rem;
        font-weight: bold;
        margin-bottom: 10px;
    }

    input[type=text],input[type=email], input[type=password] {
        padding: 15px;
        border: none;
        border-radius: 10px;
        margin-bottom: 20px;
        width: 100%;
        background-color: #F7DC6F;
        color: #2B3A42;
        font-size: 1.2rem;
    }

    input[type=submit] , input[type=reset]{
        background-color: #F7DC6F;
        color: #2B3A42;
        padding: 15px 30px;
        border: none;
        border-radius: 10px;
        font-size: 1.2rem;
        cursor: pointer;
    }

    input[type=submit]:hover , input[type=reset]:hover{
        background-color: #34495E;
        color: #F7DC6F;
    }

    .error-message {
        color: red;
        font-weight: bold;
    }
    textarea {
        padding: 15px;
        border: none;
        border-radius: 10px;
        margin-bottom: 20px;
        width: 100%;
        height: 150px; /* Hauteur du textarea */
        background-color: #F7DC6F;
        color: #2B3A42;
        font-size: 1.2rem;
        resize: vertical; /* Permet à l'utilisateur de redimensionner verticalement le textarea */
    }
    .footer {
        background-color: #333;
        color: #fff;
        padding: 20px;
        text-align: center;
    }
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
        padding-bottom: 40px;
    }

    .pagination ul {
        display: flex;
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .pagination ul li {
        margin-right: 5px;
    }

    .pagination ul li.disabled a {
        color: #ccc;
        cursor: not-allowed;
        text-decoration: none;
    }

    .pagination ul li.active a {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
        text-decoration: none;
    }

    .pagination ul li a {
        display: block;
        padding: 5px 10px;
        color: #007bff;
        background-color: #fff;
        border: 1px solid #007bff;
        text-decoration: none;
    }

    .pagination ul li a:hover {
        background-color: #007bff;
        color: #fff;
        text-decoration: none;
    }

    .article-container {
        text-align: center;
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
    }

    #pageArticle > h2 {
        font-size: 2.5rem;
        font-weight: bold;
        margin-bottom: 1rem;
    }

    #pageArticle > .sub {
        font-size: 1rem;
        color: gray;
        margin-bottom: 1rem;
    }

    #pageArticle > div {
        font-size: 1.2rem;
        line-height: 1.5;
        margin-bottom: 2rem;
    }

    #pageArticle > .pagingArticle {
        display: flex;
        justify-content: space-between;
        margin-top: 2rem;
    }

    #pageArticle > a {
        font-size: 1.2rem;
        color: black;
        text-decoration: none;
        border: 1px solid black;
        padding: 0.5rem 1rem;
        border-radius: 5px;
        transition: all 0.2s ease-in-out;

    }

    #pageArticle > a:hover {
        background-color: black;
        color: white;
    }
    #pageArticle > .disabled {
        color: gray;
        border-color: gray;
        pointer-events: none;
        opacity: 0.6;
    }
    table {
        border-collapse: collapse;
        width: 100%;
        max-width: 800px;
        margin: 0 auto;
        font-family: Arial, sans-serif;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
        font-weight: bold;
        color: #333;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    a {
        text-decoration: none;
        color: #333;
    }

    a:hover {
        color: #666;
    }
    .paging a {
        display: inline-block;
        color: blue;
        text-decoration: none;
        padding: 3px;
        border-top: 1px solid blue;
        border-bottom: 1px solid blue;
    }

    .paging {
        position: fixed;
        bottom: 40px;
        left: 0;
        right: 0;
        text-align: center;
    }

    .paging a {
        display: inline-block;
        color: blue;
        text-decoration: none;
        padding: 3px;
        border-top: 1px solid blue;
        border-bottom: 1px solid blue;
    }




</style>
