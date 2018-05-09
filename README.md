## clojure jwt

### server
```
lein ring server
```

### client(CORS)
```
http://127.0.0.1:5500/client/index.html
```

### API
| URL                       | METHOD |
|---------------------------|--------|
| /sign                     | POST   |
| /verify/:token            | POST   |

> requirements
> * ring
> * compojure
> * ring-cors
> * clj-jwt
> * axios (client)