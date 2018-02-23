use "files"
use "collections"

actor Main
    let env: Env
    
    new create(env': Env) =>
        env = env'
        
        let caps = recover val FileCaps.>set(FileRead).>set(FileStat) end

        try
            with file = OpenFile(
                FilePath(env.root as AmbientAuth, "./resources/doctors.avro", caps)?) as File
            do
                let header = recover val file.read(4) end
                if is_valid_header(header) then
                    env.out.print("Header is valid.")
                else
                    env.out.print("Header is invalid")
                end
            end
        else
            env.out.print("Something went wrong")
        end

    fun is_valid_header(first_four_bytes:Array[U8] val): Bool =>
        if first_four_bytes.size() != 4 then
            return false
        end

        let expected_header = Array[U8]
        // No way to specify an array literal?
        expected_header.push(79)
        expected_header.push(98)
        expected_header.push(106)
        expected_header.push(1)

        for i in Range(0, 4) do
            try
                if expected_header(i)? != first_four_bytes(i)? then
                    return false
                end
            else
                return false
            end
        end

        true