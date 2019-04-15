package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.*;

import java.util.*;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    class Node {
        int x, y;
        List<Point> path;

        Node(int x, int y, List<Point> path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }

    }

    private Dice dice;
    private Board board;
    private int[][] matrix = null;
    private List<String> way = new ArrayList<>();
    private final int SIZE_TO_EAT_STONE = 18;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        Point me = board.getHead();
        int y = board.inversionY(me.getY());
        int x = me.getX();
        System.out.println(board.toString());
        if (board.getSnake().size() == 0 || board.getSnakeDirection() == null) {
            way.clear();
            return "";
        }
        if (way.size() == 0) {
            getWay();
        }
        if(way.size() == 0){
            return getSideToMove(x,y);
        }
        String res = way.get(0);
        way.remove(0);
        return res;
    }

    public void getWay() {
        fillMatrix();
        int y = board.inversionY(board.getHead().getY());
        int x = board.getHead().getX();
        Point apple = board.getApples().get(0);
        int appleY = board.inversionY(apple.getY());
        int appleX = apple.getX();
        Point stone = board.getStones().get(0);
        int stoneY = board.inversionY(stone.getY());
        int stoneX = stone.getX();
        List<Point> trace = findTrace(x, y, new PointImpl(appleX, appleY));
        if(trace.size() == 0){
            trace = findTrace(x,y,new PointImpl(stoneX,stoneY));
        }
        mapPointsToDirections(trace);
    }

    public void mapPointsToDirections(List<Point> trace) {
        int y = board.inversionY(board.getHead().getY());
        int x = board.getHead().getX();
        for (Point point : trace) {
            if (point.getX() < x) {
                way.add(Direction.LEFT.toString());
                x--;
            } else if (point.getX() > x) {
                way.add(Direction.RIGHT.toString());
                x++;
            } else if (point.getY() < y) {
                way.add(Direction.UP.toString());
                y--;
            } else if (point.getY() > y) {
                way.add(Direction.DOWN.toString());
                y++;
            }
        }
    }

    public List<Point> findTrace(int x, int y, Point dest) {
        boolean[][] visited = new boolean[board.size()][board.size()];
        Queue<Node> q = new ArrayDeque<>();
        visited[y][x] = true;
        ArrayList<Point> curr = new ArrayList<>();
        curr.add(new PointImpl(x,y));
        q.add(new Node(x, y, curr));
        int[] xs = {0, 0, 1, -1};
        int[] ys = {1, -1, 0, 0};
        while (!q.isEmpty()) {
            Node node = q.poll();
            x = node.x;
            y = node.y;
            if (x == dest.getX() && y == dest.getY()) {
                return node.path;
            }
            for (int i = 0; i < 4; i++) {
                int x1 = x + xs[i];
                int y1 = y + ys[i];
                if (canMove(x1, y1) && !visited[y1][x1]){
                    visited[y1][x1] = true;
                    ArrayList<Point> newPath = new ArrayList<>(node.path);
                    newPath.add(new PointImpl(x1,y1));
                    q.add(new Node(x1,y1,newPath));
                }
            }
        }
        return new ArrayList<>();
    }

    public void fillMatrix() {
        matrix = new int[board.size()][board.size()];
        Point enemy = board.getStones().get(0);
        Point apple = board.getApples().get(0);
        List<Point> snake = board.getSnake();
        List<Point> walls = board.getWalls();
        if (snake.size() >= SIZE_TO_EAT_STONE) {
            matrix[board.inversionY(enemy.getY())][enemy.getX()] = 1;
        } else {
            matrix[board.inversionY(enemy.getY())][enemy.getX()] = -1;
        }
        matrix[board.inversionY(apple.getY())][apple.getX()] = 2;
        for (int i = 0; i < snake.size(); i++) {
            matrix[board.inversionY(snake.get(i).getY())][snake.get(i).getX()] = -3;
        }
        for (int i = 0; i < walls.size(); i++) {
            matrix[walls.get(i).getY()][walls.get(i).getX()] = -1;
        }
        matrix[board.inversionY(board.getHead().getY())][board.getHead().getX()] = 5;

        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public boolean canMove(int x, int y) {
        return matrix[y][x] >= 0;
    }

    public String getSideToMove(int x, int y) {
        String direction = "";
        HashMap<String, Integer> directions = new HashMap<>();
        directions.put(Direction.UP.toString(), spaceToMoveTop(x, y));
        directions.put(Direction.DOWN.toString(), spaceToMoveBottom(x, y));
        directions.put(Direction.LEFT.toString(), spaceToMoveLeft(x, y));
        directions.put(Direction.RIGHT.toString(), spaceToMoveRight(x, y));
        int max = directions.values().stream().mapToInt(v -> v).max().getAsInt();
        for (Map.Entry<String, Integer> entry : directions.entrySet()) {
            if (entry.getValue() == max) {
                direction = entry.getKey();
            }
        }
        return direction;
    }

    public int spaceToMoveTop(int x, int y) {
        int res = 0;
        while (canTop(x, y)) {
            res++;
            y--;
        }
        return res;
    }

    public int spaceToMoveBottom(int x, int y) {
        int res = 0;
        while (canBottom(x, y)) {
            res++;
            y++;
        }
        return res;
    }

    public int spaceToMoveLeft(int x, int y) {
        int res = 0;
        while (canLeft(x, y)) {
            res++;
            x--;
        }
        return res;
    }

    public int spaceToMoveRight(int x, int y) {
        int res = 0;
        while (canRight(x, y)) {
            res++;
            x++;
        }
        return res;
    }

    public boolean canTop(int x, int y) {
        return canMove(x, --y);
    }

    public boolean canBottom(int x, int y) {
        return canMove(x, ++y);
    }

    public boolean canLeft(int x, int y) {
        return canMove(--x, y);
    }

    public boolean canRight(int x, int y) {
        return canMove(++x, y);
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://46.101.112.224/codenjoy-contest/board/player/zttygo02xw52c0ifi5c9?code=4708271912392606670",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
