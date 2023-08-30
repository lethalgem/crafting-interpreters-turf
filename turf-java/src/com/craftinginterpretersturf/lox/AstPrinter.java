package com.craftinginterpretersturf.lox;

public class AstPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null)
            return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitRPNExpr(Expr.RPN expr) {
        return rpnPrint(expr.expression);
    }

    private String rpnPrint(Expr expr) {
        if (expr instanceof Expr.Binary) {
            Expr.Binary binaryExpr = (Expr.Binary) expr;
            return rpnPrint(binaryExpr.left) + " " +
                    rpnPrint(binaryExpr.right) + " " +
                    binaryExpr.operator.lexeme;
        } else if (expr instanceof Expr.Unary) {
            Expr.Unary unaryExpr = (Expr.Unary) expr;
            return rpnPrint(unaryExpr.right) + " " + unaryExpr.operator.lexeme;
        } else if (expr instanceof Expr.Literal) {
            Expr.Literal literalExpr = (Expr.Literal) expr;
            return literalExpr.value.toString();
        } else if (expr instanceof Expr.Grouping) {
            Expr.Grouping groupingExpr = (Expr.Grouping) expr;
            return rpnPrint(groupingExpr.expression);
        }
        return "";
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    // (1 + 2) * (4 - 3)
    public static void main(String[] args) {
        Expr expression = new Expr.RPN(
                new Expr.Binary(
                        new Expr.Binary(
                                new Expr.Literal(1),
                                new Token(TokenType.PLUS, "+", null, 1),
                                new Expr.Literal(2)),
                        new Token(TokenType.STAR, "*", null, 1),
                        new Expr.Binary(
                                new Expr.Literal(4),
                                new Token(TokenType.MINUS, "-", null, 1),
                                new Expr.Literal(3))));
        // Expr expression = new Expr.Grouping(
        // new Expr.Binary(
        // new Expr.Binary(
        // new Expr.Literal(1),
        // new Token(TokenType.PLUS, "+", null, 1),
        // new Expr.Literal(2)),
        // new Token(TokenType.STAR, "*", null, 1),
        // new Expr.Binary(
        // new Expr.Literal(4),
        // new Token(TokenType.MINUS, "-", null, 1),
        // new Expr.Literal(3))));

        // Expr expression = new Expr.Binary(
        // new Expr.Unary(
        // new Token(TokenType.MINUS, "-", null, 1),
        // new Expr.Literal(123)),
        // new Token(TokenType.STAR, "*", null, 1),
        // new Expr.Grouping(
        // new Expr.Literal(45.67)));

        System.out.println(new AstPrinter().print(expression));
    }

}
