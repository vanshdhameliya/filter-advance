# 🚀 Filter Advance

A Spring Boot project demonstrating **advanced Servlet Filter concepts** — authentication, request logging, response header manipulation, and custom filter ordering — built around a simple Student REST API.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🔐 Authentication Filter | Validates a custom token sent via request header |
| 📝 Request Logging Filter | Logs incoming request method, URI, and body |
| 📦 Response Header Filter | Injects a unique `X-Request-Id` into every response |
| ⚙️ Custom Filter Registration | Filters wired via Spring config, ordered with `@Order` |
| 👨‍🎓 Student REST API | Simple CRUD endpoints to exercise the filter chain |

---

## ⚙️ Filter Chain & `@Order`

Spring executes registered filters in ascending order of the value passed to `@Order` — **lower number runs first**. Each filter wraps the next one, so pre-processing logic runs top-down in order, while post-processing logic runs in reverse.

| Order | Filter | Runs before controller | Runs after controller |
|---|---|---|---|
| `1` | `RequestLoggingFilter` | Logs method, URI, headers, body | — |
| `2` | `AuthenticationFilter` | Validates `X-Auth-Token`, short-circuits with `401` if invalid | — |
| `3` | `ResponseHeaderFilter` | — | Adds `X-Request-Id` to the response |

**Why this order?**
- Logging runs first so every request is captured, even rejected/unauthorized ones.
- Authentication runs before the request reaches business logic, so invalid requests never touch the service layer.
- The response header filter runs closest to the controller so it can enrich the actual response, including error responses.

---

## 🔑 Authentication

Every request must include a custom header:

```
X-Auth-Token: secret-token-123
```  
