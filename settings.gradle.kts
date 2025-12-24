rootProject.name = "qa-automation-dojo"

include(
    ":public-apis:jsonplaceholder",
    ":public-apis:github-api",
    ":public-apis:petstore",
    ":public-apis:reqres",
    ":patterns:factory",
    ":patterns:builder",
    ":patterns:facade",
    ":patterns:strategy",
    ":utils:test-data-generators",
    ":utils:custom-matchers",
    ":utils:reporting"
)
