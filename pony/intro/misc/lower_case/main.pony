actor Main
    new create(env: Env) =>
        if env.args.size() != 3 then
            env.out.print("usage: lower_case <input> <output>")
            return
        end

        let values = env.args
        let in_file = (try values(1)? end).string()
        //let out_file = (try values(2)? end).string()    
        env.out.print("in_file = " + in_file)
        //senv.out.print("out_file = " + out_file)

        //let out_file = values[2]
        // let in_file = (try env.args(1)? end).string()
        // let out_file = (try env.args(2)? end).string()

        // env.out.print("in_file = " + in_file)
        // env.out.print("out_file = " + out_file)
    
