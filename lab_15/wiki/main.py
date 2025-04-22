import wikipedia
from pydantic import BaseModel
from fastapi import FastAPI

app = FastAPI()
wikipedia.set_lang("ru")


class GetPage(BaseModel):
    info: str


class GetInfo(BaseModel):
    info: list[str]


class GetInfoInput(BaseModel):
    search: str


@app.post("/", response_model=GetInfo)
def wiki(query: GetInfoInput):
    return {
        "info": wikipedia.search(query.search)
    }


@app.post("/page", response_model=GetPage)
def page(query: GetInfoInput):
    return {
        "info": wikipedia.page(query.search).content
    }
