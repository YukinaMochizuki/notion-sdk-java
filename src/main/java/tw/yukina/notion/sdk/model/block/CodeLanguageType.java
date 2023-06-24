package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CodeLanguageType {

    ABAP("abap"),
    ARDUINO("arduino"),
    BASH("bash"),
    BASIC("basic"),
    C("c"),
    CLOJURE("clojure"),
    COFFEESCRIPT("coffeescript"),
    CPP("c++"),
    C_SHARP("c#"),
    CSS("css"),
    DART("dart"),
    DIFF("diff"),
    DOCKER("docker"),
    ELIXIR("elixir"),
    ELM("elm"),
    ERLANG("erlang"),
    FLOW("flow"),
    FORTRAN("fortran"),
    F_SHARP("f#"),
    GHERKIN("gherkin"),
    GLSL("glsl"),
    go("GO"),
    GRAPHQL("graphql"),
    GROOVY("groovy"),
    HASKELL("haskell"),
    HTML("html"),
    JAVA("java"),
    JAVASCRIPT("javascript"),
    JSON("json"),
    JULIA("julia"),
    KOTLIN("kotlin"),
    LATEX("latex"),
    LESS("less"),
    LISP("lisp"),
    LIVESCRIPT("livescript"),
    LUA("lua"),
    MAKEFILE("makefile"),
    MARKDOWN("markdown"),
    MARKUP("markup"),
    MATLAB("matlab"),
    MERMAID("mermaid"),
    NIX("nix"),
    OBJECTIVE_C("objective-c"),
    OCAML("ocaml"),
    PASCAL("pascal"),
    PERL("perl"),
    PHP("php"),
    PLAIN_TEXT("plain text"),
    POWERSHELL("powershell"),
    PROLOG("prolog"),
    PROTOBUF("protobuf"),
    PYTHON("python"),
    R("r"),
    RUBY("ruby"),
    RUST("rust"),
    SASS("sass"),
    SCALA("scala"),
    SCHEME("scheme"),
    SCSS("scss"),
    SHELL("shell"),
    SQL("sql"),
    SWIFT("swift"),
    TYPESCRIPT("typescript"),
    VB_DOT_NET("vb.net"),
    VERILOG("verilog"),
    VHDL("vhdl"),
    VISULA_BASIC("visual basic"),
    WEBASSEMBLY("webassembly"),
    XML("xml"),
    YAML("yaml"),
    JAVA_C_CPP_CSHAP("java/c/c++/c#");


    private final String field;

    CodeLanguageType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }

}
